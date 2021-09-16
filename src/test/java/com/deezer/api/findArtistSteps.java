package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class findArtistSteps {
        private long artist_id;

        @Когда("Найти исполнителя с именем {string}")
        public void findArtistTest(String string) {
                String body = given()
                        .when().get("/search/artist?q=the_beatles").getBody().asString();
                artist_id = Long.parseLong(body.substring(body.indexOf("id") + 4, body.indexOf(',', body.indexOf(("id")))));
        }
        @Тогда("^Уникальный идентификатор исполнителя равен (\\d+)$")
        public void checkIdOfArtist(Integer arg0) {
                Assertions.assertEquals(1, artist_id, "Artist Id doesn't match");
        }
}
