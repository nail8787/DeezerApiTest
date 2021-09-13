package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

import java.math.BigInteger;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class deletePlaylistSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .build();

    private long playlist_id;
    @Дано("В плейлистах пользователя есть плейлист с названием {string}")
    public void playlistIsPresentTest(String arg0) {
        given().spec(requestSpecification)
                .when().get("/user/4571342102/playlists").then().assertThat().body("data.title", hasItem(arg0));
    }

    @Когда("Удалить плейлист с идентификатором {biginteger}")
    public void deletePlaylistAttempt(BigInteger arg0){

        JsonPath body = new JsonPath(given().spec(requestSpecification)
                        .when().get("/user/4571342102/playlists").getBody().asString());
        HashMap<String,?> my_playlist = body.get("data.find { it.title == 'my_playlist' } ");
        playlist_id = (long) my_playlist.get("id");
        given().spec(requestSpecification)
                .when().delete("/playlist/" + Long.toString(playlist_id) + "&access_token=frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист с названием {string} отсутствует в списке плейлистов пользователя {string}")
    public void findDeletedPlaylistTest(String arg0, String arg1) {
        given().spec(requestSpecification)
                .when().get("/user/" + arg1 + "/playlists")
                .then().assertThat().body("data.title", is(not(hasItem(arg0)))).and().body("data.id", is(not(hasItem(Long.toString(playlist_id)))));
    }
}
