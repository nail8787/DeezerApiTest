package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class addPlaylistToFavoriteSteps {

    @Когда("Добавить плейлист {string} в любимые плейлисты пользователя {string}")
    public void addToFaroviteTest(String playlist_id, String user_id) {
        given().params("playlist_id", playlist_id)
                .when().post("/user/" + user_id + "/playlists")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист {long} отображается в любимых плейлистах пользователя {string}")
    public void checkFavoritePlaylist(long playlist_id, String user_id){
            given()
                    .when().get("/user/" + user_id + "/playlists")
                    .then().assertThat().body("data.id", hasItem(playlist_id));
    }
}
