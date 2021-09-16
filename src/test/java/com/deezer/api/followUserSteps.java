package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class followUserSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .addParam("access_token", "frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF")
            .build();

    @Когда("Пользователь {string} добавляет другого пользователя {string} в список отслеживаемых")
    public void followUserTest(String user_id, String following_user_id) {
        given().spec(requestSpecification).params("user_id", following_user_id)
                .when().post("/user/" + user_id + "/followings")
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("В списке отслеживаемых пользователя {string} присутствует пользователь {string}")
    public void checkFollowingList(String user_id, String following_user_id) {
        given().spec(requestSpecification)
                .when().get("/user/" + user_id + "/followings")
                .then().assertThat().body("data.id", hasItem(Long.parseLong(following_user_id)));
    }
}
