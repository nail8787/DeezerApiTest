package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

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

    @After ("@playlist")
    public void tearDown() {
        String body = given().get("/user/4571342102/playlists").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Long> playlist_ids = response.getList("data.id");
        for (Long playlist_id: playlist_ids) {
            if (playlist_id != Long.parseLong("9460098222")) {
                System.out.println("Deleting playlist " + playlist_id.toString() + " after tests");
                given().when().delete("/playlist/" + playlist_id.toString());
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
            given().params("user_id", followings_id)
                    .when().delete("/user/4571342102/followings");
        }
    }
}
