package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class followUserSteps {

    @Когда("Пользователь {string} добавляет другого пользователя {string} в список отслеживаемых")
    public void followUserTest(String user_id, String following_user_id) {
        given().params("user_id", following_user_id).pathParam("id", user_id)
                .when().post(EndPoints.followings)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("В списке отслеживаемых пользователя {string} присутствует пользователь {string}")
    public void checkFollowingList(String user_id, String following_user_id) {
        given().pathParam("id", user_id)
                .when().get(EndPoints.followings)
                .then().assertThat().body("data.id", hasItem(Long.parseLong(following_user_id)));
    }
}
