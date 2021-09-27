package com.deezer.api.stepdefs;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import com.deezer.api.endpoints.EndPoints;
import com.deezer.api.helpers.playlistFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

public class createPlaylistSteps {

    public String playlist_id;
    @Когда("Создать плейлист с названием {string}")
    public void createPlaylistTest(String playlist_name){
        playlist_id = playlistFactory.createPlaylist(playlist_name);
    }

    @Тогда("Плейлист с названием {string} отображается в плейлистах пользователся с id {long}")
    public void checkThatPlaylistIsInUsersProfile(String title, long id) {
        given().pathParam("id", Long.toString(id))
                .when().get(EndPoints.playlists)
                .then().assertThat().body("data.title", hasItem(title));
    }
}
