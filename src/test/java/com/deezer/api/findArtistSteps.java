package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import utilities.EndPoints;
import utilities.Searching;

import static io.restassured.RestAssured.given;

public class findArtistSteps {
        private long artist_id;

        @Когда("Найти исполнителя с именем {string}")
        public void findArtistTest(String artist_name) {
                String body = given().param("q", artist_name)
                        .when().get(EndPoints.searchArtist).getBody().asString();
                artist_id = Long.parseLong(body.substring(body.indexOf("id") + 4, body.indexOf(',', body.indexOf(("id")))));
        }
        @Тогда("В ответе корректный уникальный идентификатор артиста {string}")
        public void checkIdOfArtist(String artist_name) {
                long artistIdFromData = Searching.findArtistByName(artist_name);
                Assertions.assertEquals(artistIdFromData, artist_id, "Artist Id doesn't match");
        }
}
