package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class unFollowUserSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token", "frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    @Дано("Пользователь {string} отслеживает другого пользователя {string}")
    public void userFollowedAnotherUser(String user_id, String following_user_id) {
        given().spec(requestSpecification).params("user_id", following_user_id)
                .when().post("/user/" + user_id + "/followings")
                .then().assertThat().body(equalTo("true"));
        given().spec(requestSpecification)
                .when().get("/user/" + user_id + "/followings")
                .then().log().body();
    }

    @Когда("Пользователь {string} прекращает отслеживать пользователя {string}")
    public void unfollowUserTest(String user_id, String following_user_id) {
        given().spec(requestSpecification).params("user_id", following_user_id)
                .when().delete("/user/" + user_id + "/followings")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Ранее отслеживаемый пользователь {string} не отображается в списке отслеживаемых пользователя {string}")
    public void checkForUnfollowingUser(String following_user_id, String user_id) {
        given().spec(requestSpecification)
                .when().get("/user/" + user_id + "/followings")
                .then().assertThat().body("data.id", not(hasItem(Long.parseLong(following_user_id))));
    }
}
