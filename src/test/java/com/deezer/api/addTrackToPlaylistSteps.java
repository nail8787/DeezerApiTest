package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;

import utilities.EndPoints;
import utilities.Searching;
import utilities.playlistFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class addTrackToPlaylistSteps {

    private String playlist_id;
    private ValidatableResponse error_body;

    @Дано("Создан плейлист с названием {string}")
    public void playlistCreated(String playlist_name) {
        playlist_id = playlistFactory.createPlaylist(playlist_name);
    }

    @Когда("Добавить песню {string} в плейлист")
    public void addTrackToPlaylistTest(String trackName) {
        String trackId = Searching.findTrackByName(trackName);
        given().param("songs", trackId).pathParam("playlistId", playlist_id)
                .when().post(EndPoints.playlistTracks)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Песня {string} отображается в плейлисте")
    public void trackIsPresentInPlaylist(String trackName) {
        String trackId = Searching.findTrackByName(trackName);
        given().pathParam("playlistId", playlist_id)
                .when().get(EndPoints.playlist)
                .then().assertThat().body("tracks.data.id", hasItem(Integer.parseInt(trackId)));
    }

    @Дано("Существует плейлист с названием {string}, в котором присутствует песня с идентификатором {string}")
    public void playlistSetUpAddedTrackToPlaylist(String playlist_name, String track_id) {
        String body = given().pathParam("id", EndPoints.my_id).param("title", playlist_name)
                .when().post(EndPoints.playlists)
                .body().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        given().param("songs", track_id).pathParam("playlistId", playlist_id)
                .when().post(EndPoints.playlistTracks)
                .then().assertThat().body(equalTo("true"));
    }

    @Когда("Пользователь добавляет уже ранее добавленную песню {string} в плейлист")
    public void userAddsTrackThatAlreadyExistsInPlaylist(String track_id) {
        error_body = given().param("songs", track_id).pathParam("playlistId", playlist_id)
                .when().post(EndPoints.playlistTracks).then();
    }

    @Тогда("В ответе присутствует описание ошибки")
    public void responseContainsErrorDescription() {
        error_body.assertThat().body("error.message", equalTo("This song already exists in this playlist"));
    }
}
