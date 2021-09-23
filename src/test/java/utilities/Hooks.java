package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import utilities.JsonReader.JsonReaderAlbum;
import utilities.JsonReader.JsonReaderArtist;
import utilities.JsonReader.JsonReaderTrack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Hooks {
    @Before
    public void setUp() {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("baseURI"))
                .setRelaxedHTTPSValidation()
                .addParam("access_token", properties.getProperty("access_token"))
                .build();
        RestAssured.requestSpecification = requestSpecification;
    }

    @Before("@tracks")
    public void setUpTracks() {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        JsonReaderTrack.getJson(properties.getProperty("testDataPath"));
    }

    @Before("@artist")
    public void setUpArtist() {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        JsonReaderArtist.getJson(properties.getProperty("testDataPath"));
    }

    @Before("@albums")
    public void setUpAlbums() {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        JsonReaderAlbum.getJson(properties.getProperty("testDataPath"));
    }

    @After ("@playlist")
    public void tearDown() {
        String body = given().get("/user/4571342102/playlists").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Long> playlist_ids = response.getList("data.id");
        for (Long playlist_id: playlist_ids) {
            if (playlist_id != Long.parseLong("9460098222")) {
                System.out.println("Deleting playlist " + playlist_id.toString() + " after tests");
                given().pathParam("playlistId", playlist_id.toString())
                        .when().delete(EndPoints.playlist);
            }
        }
    }

    @After ("@favoritePlaylist")
    public void tearDownFavoritePlaylist() {
        String body = given().get("/user/4571342102/playlists").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Long> playlist_ids = response.getList("data.id");
        for (Long playlist_id: playlist_ids) {
            if (playlist_id != Long.parseLong("9460098222")) {
                System.out.println("Deleting playlist " + playlist_id.toString() + " from favorite after tests");
                given().pathParam("id", EndPoints.my_id).param("playlist_id", playlist_id.toString())
                        .when().delete(EndPoints.playlists);
            }
        }
    }

    @After("@follow")
    public void tearDownFollow() {
        String body = given().get("/user/4571342102/followings").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Long> followings_ids = response.getList("data.id");
        for (Long followings_id: followings_ids) {
            System.out.println("Unfollow user " + followings_id.toString() + " after tests");
            given().params("user_id", followings_id).pathParam("id", EndPoints.my_id)
                    .when().delete(EndPoints.followings);
        }
    }

    @After("@albums")
    public void tearDownAlbums() {
        String body = given().get("/user/4571342102/albums").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Integer> albums_ids = response.getList("data.id");
        for (Integer albums_id: albums_ids) {
            System.out.println("Delete album from library " + albums_id.toString() + " after tests");
            given().params("album_id", albums_id).pathParam("id", EndPoints.my_id)
                    .when().delete(EndPoints.albums);
        }
    }
}
