package com.deezer.api;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class unFollowUserSteps {
    @Дано("Пользователь {string} отслеживает другого пользователя {string}")
    public void userFollowedAnotherUser(String user_id, String following_user_id) {
        given().params("user_id", following_user_id).pathParam("id", user_id)
                .when().post(EndPoints.followings)
                .then().assertThat().body(equalTo("true"));
    }

    @Когда("Пользователь {string} прекращает отслеживать пользователя {string}")
    public void unfollowUserTest(String user_id, String following_user_id) {
        given().params("user_id", following_user_id).pathParam("id", user_id)
                .when().delete(EndPoints.followings)
                .then().assertThat().body(equalTo("true"));
    }

    @Тогда("Ранее отслеживаемый пользователь {string} не отображается в списке отслеживаемых пользователя {string}")
    public void checkForUnfollowingUser(String following_user_id, String user_id) {
        given().pathParam("id", user_id)
                .when().get(EndPoints.followings)
                .then().assertThat().body("data.id", not(hasItem(Long.parseLong(following_user_id))));
    }
}
