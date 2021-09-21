package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import utilities.JsonReader;
import utilities.Track;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class addTrackToPlaylistSteps {

    private String playlist_id;
    private ValidatableResponse error_body;

    private String findTrackByName(String trackName) {
        List<Track> tracks = Arrays.asList(JsonReader.tracks);
        String trackId = "";
        for (Track current : tracks) {
            if (current.getTrackName().equals(trackName))
                trackId = current.getTrackId();
        }
        System.out.println("trackId = " + trackId);
        return trackId;
    }

    @Дано("Создан плейлист с названием {string}")
    public void playlistCreated(String playlist_name) {
        String body = given()
                .when().post("/user/4571342102/playlists?title=" + playlist_name)
                .body().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Created playlist " + playlist_name + " with id-" + playlist_id);
    }

    @Когда("Добавить песню с идентификатором {string} в плейлист")
    public void addTrackToPlaylistTest(String trackName) {
        String trackId = findTrackByName(trackName);
        given().param("songs", trackId)
                .when().post("/playlist/" + playlist_id + "/tracks")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Песня c идентификатором {string} отображается в плейлисте")
    public void trackIsPresentInPlaylist(String trackName) {
        String trackId = findTrackByName(trackName);
        given().when().get("/playlist/" + playlist_id)
                .then().assertThat().body("tracks.data.id", hasItem(Integer.parseInt(trackId)));
    }

    @Дано("Существует плейлист с названием {string}, в котором присутствует песня с идентификатором {string}")
    public void playlistSetUpAddedTrackToPlaylist(String playlist_name, String track_id) {
        String body = given()
                .when().post("/user/4571342102/playlists?title=" + playlist_name)
                .body().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        given().param("songs", track_id)
                .when().post("/playlist/" + playlist_id + "/tracks")
                .then().assertThat().body(equalTo("true"));
    }

    @Когда("Пользователь добавляет уже ранее добавленную песню {string} в плейлист")
    public void userAddsTrackThatAlreadyExistsInPlaylist(String track_id) {
        error_body = given().param("songs", track_id)
                .when().post("/playlist/" + playlist_id + "/tracks").then();
    }

    @Тогда("В ответе присутствует описание ошибки")
    public void responseContainsErrorDescription() {
        error_body.assertThat().body("error.message", equalTo("This song already exists in this playlist"));
    }
}
