package com.deezer.api.helpers;

import com.deezer.api.endpoints.EndPoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class playlistFactory {

    public static String createPlaylist(String playlist_name){
        Response response = given().pathParam("id", EndPoints.my_id).param("title", playlist_name)
                .when().post(EndPoints.playlists);
        response.then().assertThat().body("$", hasKey("id"));
        String body = response.getBody().asString();
        String playlist_id = body.substring(body.indexOf("id") + 4, body.indexOf("}"));
        System.out.println("Created playlist '" + playlist_name + "' with id-" + playlist_id);
        return playlist_id;
    }
}
