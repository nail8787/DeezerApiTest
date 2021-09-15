package utilities;

import io.cucumber.java.After;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Hooks {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
        .setBaseUri("https://api.deezer.com")
        .setRelaxedHTTPSValidation()
        .addParam("access_token","frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
        .build();

    @After ("@playlist")
    public void tearDown() {
        String body = given().spec(requestSpecification)
                .get("/user/4571342102/playlists").getBody().asString();
        JsonPath response = new JsonPath(body);
        List<Long> playlist_ids = response.getList("data.id");
        for (Long playlist_id: playlist_ids) {
            if (playlist_id != Long.parseLong("9460098222")) {
                System.out.println("Deleting playlist " + playlist_id.toString() + " after tests");
                given().spec(requestSpecification)
                        .when().delete("/playlist/" + playlist_id.toString());
            }
        }
    }
}
