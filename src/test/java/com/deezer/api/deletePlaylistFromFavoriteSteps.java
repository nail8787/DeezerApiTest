package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class deletePlaylistFromFavoriteSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token", "frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    @Дано("Плейлист {string} присутствует в любимых плейлистах пользователя {string}")
    public void addPlaylistToFavoriteToTest(String playlist_id, String user_id) {
        given().spec(requestSpecification).params("playlist_id", playlist_id)
                .when().post("/user/" + user_id + "/playlists")
                .then().assertThat().body(equalTo("true"));
    }

    @Когда("Удаление плейлиста {string} из любимых пользователя {string}")
    public void deletePlaylistFromFavoriteTest(String playlist_id, String user_id) {
        given().spec(requestSpecification).params("playlist_id", playlist_id)
                .when().delete("/user/" + user_id + "/playlists")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист {string} не отображается в любимых плейлистах пользователя {string}")
    public void checkThatPlaylistIsRemovedFromFavorites(String playlist_id, String user_id) {
        given().spec(requestSpecification)
                .when().get("/user/" + user_id + "/playlists")
                .then().assertThat().body("data.id", not(hasItem(Long.parseLong(playlist_id))));
    }
}
