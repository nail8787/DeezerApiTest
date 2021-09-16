package com.deezer.api;

import io.cucumber.java.bg.И;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class deleteTrackFromPlaylistSteps {

    private String playlist_id;

    @Дано("Существует плейлист с название {string} у пользователя с идентификатором {string}")
    public void createPlaylistForTest(String playlist_name, String user_id) {
       Response response = given()
               .when().post("/user/" + user_id + "/playlists?title=" + playlist_name);
       response.then().assertThat().body("$", hasKey("id"));
       String body = response.getBody().asString();
       playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
       System.out.println("Created playlist with id-" + playlist_id);
    }

    @И("В плейлисте присутствует песня с идентификатором {string}")
    public void addTrackForTest(String track_id) {
        given().param("songs", track_id)
               .when().post("/playlist/" + playlist_id + "/tracks")
               .then().assertThat().body(equalTo("true"));
        System.out.println("Track " + playlist_id + " added");
   }

   @Когда("Удалить песню с идентификатором {string} из плейлиста Delete track from playlist")
   public void deleteTrackFromPlaylistTest(String track_id){
        given().param("songs", track_id)
                .when().delete("/playlist/" + playlist_id + "/tracks")
                .then().assertThat().body(equalTo("true"));
   }

   @Тогда("Песня с идентификатором {string} не отображается в плейлисте Delete track from playlist")
    public void checkIfTrackRemoved(String track_id){
        given()
                .when().get("/playlist/" + playlist_id)
                .then().assertThat().body("tracks.data.id", not(hasItem(Integer.parseInt(track_id))));
   }
}