package com.deezer.api;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class createPlaylistSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token","frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    public String playlist_id;
    private Response response;
    @Когда("Создать плейлист с названием {string}")
    public void createPlaylistTest(String string){
        response = given().spec(requestSpecification)
                .when().post("/user/4571342102/playlists?title=" + string);
        response.then().assertThat().body("$", hasKey("id"));
        String body = response.getBody().asString();
        playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Created playlist with id-" + playlist_id);
    }

    @Тогда("Плейлист с названием {string} отображается в плейлистах пользователся с id {long}")
    public void checkThatPlaylistIsInUsersProfile(String title, long id) {
        given().spec(requestSpecification).
                when().get("/user/" + id + "/playlists")
                .then().assertThat().body("data.title", hasItem(title));
    }

    @After("@playlist")
    public void tearDownPlaylistAdd(){
        System.out.println("Deleting playlist after tests");
        given().spec(requestSpecification)
                .when().delete("/playlist/" + playlist_id);
    }
}
