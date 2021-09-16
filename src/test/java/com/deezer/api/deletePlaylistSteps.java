package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class deletePlaylistSteps {

    private String playlist_id;
    @Дано("В плейлистах пользователя есть плейлист с названием {string}")
    public void playlistIsPresent(String playlist_name) {
        String body = given()
                .when().post("/user/4571342102/playlists?title=" + playlist_name)
                .body().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Плейлист с названием " + playlist_name + " создан и имеет id-" + playlist_id);
    }

    @Когда("Удалить плейлист c названием Delete test")
    public void deletePlaylistAttempt(){
        given()
                .when().delete("/playlist/" + playlist_id)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист отсутствует в списке плейлистов пользователя {string}")
    public void findDeletedPlaylistTest(String user_id) {
        given()
                .when().get("/user/" + user_id + "/playlists")
                .then().assertThat().body("data.id", is(not(hasItem(playlist_id))));
    }
}
