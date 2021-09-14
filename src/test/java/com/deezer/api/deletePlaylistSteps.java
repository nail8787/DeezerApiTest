package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class deletePlaylistSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token","frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    private String playlist_id;
    @Дано("В плейлистах пользователя есть плейлист с названием {string}")
    public void playlistIsPresent(String playlist_name) {
        String body = given().spec(requestSpecification)
                .when().post("/user/4571342102/playlists?title=" + playlist_name)
                .body().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Плейлист с названием " + playlist_name + " создан и имеет id-" + playlist_id);
    }

    @Когда("Удалить плейлист c названием Delete test")
    public void deletePlaylistAttempt(){
        given().spec(requestSpecification)
                .when().delete("/playlist/" + playlist_id)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист отсутствует в списке плейлистов пользователя {string}")
    public void findDeletedPlaylistTest(String user_id) {
        given().spec(requestSpecification)
                .when().get("/user/" + user_id + "/playlists")
                .then().assertThat().body("data.id", is(not(hasItem(playlist_id))));
    }
}
