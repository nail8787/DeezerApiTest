package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.Album;
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

    @Когда("Добавить альбом {string}")
    public void addAlbumToLibrary(String albumName) {
        String albumId = findAlbumByName(albumName);
        given().param("album_id", albumId)
                .when().post("/user/4571342102/albums")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Альбом {string} отображается в библиотеке пользователя {string}")
    public void checkAlbum(String albumName, String user_id) {
        String albumId = findAlbumByName(albumName);
        given().when().get("/user/" + user_id + "/albums")
                .then().assertThat().body("data.id", hasItem(Integer.parseInt(albumId)));
    }
}
