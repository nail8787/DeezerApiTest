package com.deezer.api;

import io.cucumber.java.bg.И;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import utilities.EndPoints;
import utilities.playlistFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class deleteTrackFromPlaylistSteps {

    private String playlist_id;

    @Дано("Существует плейлист с название {string} у пользователя с идентификатором {string}")
    public void createPlaylistForTest(String playlist_name, String user_id) {
        playlist_id = playlistFactory.createPlaylist(playlist_name);
    }

    @И("В плейлисте присутствует песня с идентификатором {string}")
    public void addTrackForTest(String track_id) {
        given().param("songs", track_id).pathParam("playlistId", playlist_id)
               .when().post(EndPoints.playlistTracks)
               .then().assertThat().body(equalTo("true"));
        System.out.println("Track " + playlist_id + " added");
   }

   @Когда("Удалить песню с идентификатором {string} из плейлиста Delete track from playlist")
   public void deleteTrackFromPlaylistTest(String track_id){
        given().param("songs", track_id).pathParam("playlistId", playlist_id)
                .when().delete(EndPoints.playlistTracks)
                .then().assertThat().body(equalTo("true"));
   }

   @Тогда("Песня с идентификатором {string} не отображается в плейлисте Delete track from playlist")
    public void checkIfTrackRemoved(String track_id){
        given().pathParam("playlistId", playlist_id)
                .when().get(EndPoints.playlist)
                .then().assertThat().body("tracks.data.id", not(hasItem(Integer.parseInt(track_id))));
   }
}