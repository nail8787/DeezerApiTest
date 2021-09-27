package com.deezer.api.stepdefs;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import com.deezer.api.endpoints.EndPoints;
import com.deezer.api.helpers.Searching;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class findArtistSteps {
        private long artist_id;
        ValidatableResponse failedBody;

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

        @Когда("Найти исполнителя с несуществующим именем {string}")
        public void findNonExistentArtist(String artistName) {
                failedBody = given().param("q", artistName)
                        .when().get(EndPoints.searchArtist).then();
        }

        @Тогда("В ответе нет ни одной записи")
        public void noEntriesInResponse() {
                failedBody.assertThat().body("total", equalTo(0));
        }
}
