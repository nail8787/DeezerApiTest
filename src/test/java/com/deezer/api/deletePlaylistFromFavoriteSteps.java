package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class deletePlaylistFromFavoriteSteps {

    @Дано("Плейлист {string} присутствует в любимых плейлистах пользователя {string}")
    public void addPlaylistToFavoriteToTest(String playlist_id, String user_id) {
        given().params("playlist_id", playlist_id).pathParam("id", user_id)
                .when().post(EndPoints.playlists)
                .then().assertThat().body(equalTo("true"));
    }

    @Когда("Удаление плейлиста {string} из любимых пользователя {string}")
    public void deletePlaylistFromFavoriteTest(String playlist_id, String user_id) {
        given().params("playlist_id", playlist_id).pathParam("id", user_id)
                .when().delete(EndPoints.playlists)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист {string} не отображается в любимых плейлистах пользователя {string}")
    public void checkThatPlaylistIsRemovedFromFavorites(String playlist_id, String user_id) {
        given().pathParam("id", user_id)
                .when().get(EndPoints.playlists)
                .then().assertThat().body("data.id", not(hasItem(Long.parseLong(playlist_id))));
    }
}
