package com.deezer.api;

import io.cucumber.java.af.En;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.Album;
import utilities.EndPoints;
import utilities.JsonReaderAlbum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class addAlbumToLibrary {

    private String findAlbumByName(String albumName) {
        String albumId = "";
        for (Album current : JsonReaderAlbum.albums) {
            if (current.getAlbumName().equals(albumName))
                albumId = current.getAlbumId();
        }
        return albumId;
    }

    @Когда("Добавить альбом {string} в библиотеку пользователя {string}")
    public void addAlbumToLibrary(String albumName, String user_id) {
        String albumId = findAlbumByName(albumName);
        given().param("album_id", albumId).pathParam("id", user_id)
                .when().post(EndPoints.albums)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Альбом {string} отображается в библиотеке пользователя {string}")
    public void checkAlbum(String albumName, String user_id) {
        String albumId = findAlbumByName(albumName);
        given().pathParam("id", user_id)
                .when().get(EndPoints.albums)
                .then().assertThat().body("data.id", hasItem(Integer.parseInt(albumId)));
    }
}
