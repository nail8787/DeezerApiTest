package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
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

    @Когда("Создать плейлист с названием {string}")
    public void createPlaylistTest(String string){
        given().spec(requestSpecification)
                .when().log().all().post("/user/4571342102/playlists?title=" + string)
                .then().assertThat().body("$", hasKey("id"));
    }

    @Тогда("Плейлист с названием {string} отображается в плейлистах пользователся с id {long}")
    public void плейлистСНазваниемMy_playlistОтображаетсяВПлейлистахПользователсяСId(String title, long id) {
        given().spec(requestSpecification).
                when().get("/user/" + id + "/playlists")
                .then().assertThat().body("data.title", hasItem("my_playlist"));
    }
}
