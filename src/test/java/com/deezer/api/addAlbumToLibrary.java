package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;
import utilities.Searching;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class addAlbumToLibrary {

    @Когда("Добавить альбом {string} в библиотеку пользователя {string}")
    public void addAlbumToLibrary(String albumName, String user_id) {
        String albumId = Searching.findAlbumByName(albumName);
        given().param("album_id", albumId).pathParam("id", user_id)
                .when().post(EndPoints.albums)
                .then().assertThat().body(equalTo("true"));
        System.out.println("Album \"" + albumName + "\" added to library");
    }

    @Тогда("Альбом {string} отображается в библиотеке пользователя {string}")
    public void checkAlbum(String albumName, String user_id) {
        String albumId = Searching.findAlbumByName(albumName);
        given().pathParam("id", user_id)
                .when().get(EndPoints.albums)
                .then().assertThat().body("data.id", hasItem(Integer.parseInt(albumId)));
    }
}
