package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class findArtistSteps {
        long artist_id;
        @Когда("Поиск исполнителя с именем {string}")
        public void findArtistTest(String string) {
                String body = given().relaxedHTTPSValidation().baseUri("https://api.deezer.com")
                        .when().get("/search/artist?q=the_beatles").getBody().asString();
                System.out.println(body);
                artist_id = Long.parseLong(body.substring(body.indexOf("id") + 4, body.indexOf(',', body.indexOf(("id")))));
        }
        @Тогда("^Уникальный идентификатор равен (\\d+)$")
        public void checkIdOfArtist(Integer arg0) {
                Assertions.assertEquals(1, artist_id, "Artist Id doesn't match");
        }
}
