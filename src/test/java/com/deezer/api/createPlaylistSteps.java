package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class createPlaylistSteps {
        double  new_playlist_id;
        @Когда("Делается запрос на создание плейлиста c названием {string}")
        public void createPlaylistTest(String string){
            String body = given().relaxedHTTPSValidation().baseUri("https://api.deezer.com")
                    .when().post("/user/4571342102/playlists?title=" + string +
                            "&access_token=frR2ykEVO9mC4uAR4FcEc0tAGdTgBaWBoPabuatXLtae2DscRQ").getBody().asString();
            new_playlist_id =  Double.parseDouble(body.substring(6, body.indexOf('}')));
        }
        @Тогда("Плейлист c названием {string} создан и ему присвоен уникальный идентификатор")
        public void checkNewPlaylistTest(String string) {
            String body = given().relaxedHTTPSValidation().baseUri("https://api.deezer.com")
                    .when().get("/playlist/" + Double.toString(new_playlist_id)).getBody().asString();
            assertThat(body, containsString("\"title\":\"" + string + "\""));
        }

}
