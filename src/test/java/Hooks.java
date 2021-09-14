import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import java.lang.reflect.Field;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class Hooks {
    //Не знаю как передать сюда информацию
//    @After("@playlist")
//    public void tearDown(Scenario scenario) {
//        Field[] fields = scenario.getClass().getDeclaredFields();
//        Arrays.stream(fields).allMatch("playlist_id");
//        Field playlist_id = scenario.getClass().getDeclaredFields();
//        Field playlist_id = scenario.getClass().getDeclaredFields("playlist_id");
//            System.out.println("Deleting playlist after tests");
//            given().spec(requestSpecification)
//                    .when().delete("/playlist/" + playlist_id);
//    }
}
