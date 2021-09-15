package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.beans.IntrospectionException;
import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class addPlaylistToFavoriteSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token", "frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    @Когда("Добавить плейлист {string} в любимые плейлисты пользователя {string}")
    public void addToFaroviteTest(String playlist_id, String user_id) {
        given().spec(requestSpecification).params("playlist_id", playlist_id)
                .when().post("/user/" + user_id + "/playlists").then().assertThat().body(equalTo("true"));
    }

    @Тогда("Плейлист {long} отображается в любимых плейлистах пользователя {string}")
    public void checkFavoritePlaylist(long playlist_id, String user_id){
            given().spec(requestSpecification)
                    .when().get("/user/" + user_id + "/playlists")
                    .then().assertThat().body("data.id", hasItem(playlist_id));
    }
}
