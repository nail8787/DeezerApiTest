package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class createPlaylistSteps {

    public String playlist_id;
    @Когда("Создать плейлист с названием {string}")
    public void createPlaylistTest(String playlist_name){
        Response response = given()
                .when().post("/user/4571342102/playlists?title=" + playlist_name);
        response.then().assertThat().body("$", hasKey("id"));
        String body = response.getBody().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Created playlist with id-" + playlist_id);
    }

    @Тогда("Плейлист с названием {string} отображается в плейлистах пользователся с id {long}")
    public void checkThatPlaylistIsInUsersProfile(String title, long id) {
        given()
                .when().get("/user/" + id + "/playlists")
                .then().assertThat().body("data.title", hasItem(title));
    }
}
