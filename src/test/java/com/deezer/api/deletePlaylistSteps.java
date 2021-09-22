package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;
import utilities.playlistFactory;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class deletePlaylistSteps {

    private String playlist_id;
    @Дано("В плейлистах пользователя есть плейлист с названием {string}")
    public void playlistIsPresent(String playlist_name) {
        playlist_id = playlistFactory.createPlaylist(playlist_name);
    }

    @Когда("Удалить плейлист c названием Delete test")
    public void deletePlaylistAttempt(){
        given().pathParam("playlistId", playlist_id)
                .when().delete(EndPoints.playlist)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист отсутствует в списке плейлистов пользователя {string}")
    public void findDeletedPlaylistTest(String user_id) {
        given().pathParam("id", user_id)
                .when().get(EndPoints.playlists)
                .then().assertThat().body("data.id", is(not(hasItem(playlist_id))));
    }
}
