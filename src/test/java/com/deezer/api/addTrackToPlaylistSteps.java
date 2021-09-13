package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class addTrackToPlaylistSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token","frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    private ResponseBody error_body;
    @Когда("Добавить песню с идентификатором {string} в плейлист c идентификатором {string}")
    public void addTrackToPlaylistTest(String track_id, String playlist_id) {
        given().spec(requestSpecification).param("songs", track_id)
                .when().post("/playlist/" + playlist_id + "/tracks")
                .then().assertThat().body(equalTo("true"));
    }


    @Тогда("Песня c идентификатором {string} отображается в плейлисте {string}")
    public void trackIsPresentInPlaylist(String track_id, String playlist_id) {
            given().spec(requestSpecification)
                    .when().get("/playlist/" + playlist_id)
                    .then().assertThat().body("tracks.data.id", hasItem(Integer.parseInt(track_id)));
    }

    @Когда("Пользователь добавляет уже ранее добавленную песню ({string}) в плейлист {string}")
    public void userAddsTrackThatAlreadyExistsInPlaylist(String track_id, String playlist_id) {
        error_body = given().spec(requestSpecification).param("songs", track_id)
                .when().post("/playlist/" + playlist_id + "/tracks").getBody();
    }

    @Тогда("В ответе присутствует описание ошибки")
    public void responseContainsErrorDescription() {
//        error_body
    }
}
