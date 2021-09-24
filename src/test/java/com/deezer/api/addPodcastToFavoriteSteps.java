package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import utilities.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/** Похоже данная функция не работает пока в API **/

public class addPodcastToFavoriteSteps {
        @Когда("Добавить подкаст {string} в любимые пользователя")
        public void addPodcastToFavoriteTest(String podcastId) {
                given().pathParam("id", EndPoints.my_id).param("podcast_id", podcastId)
                        .when().post(EndPoints.podcasts)
                        .then().assertThat().body(equalTo("true"));
        }
        @Тогда("Подкаст {string} отражается в любимых пользователя")
        public void checkPodcastInUsersFavorite(String podcastId) {
                given().pathParam("id", EndPoints.my_id)
                        .when().get(EndPoints.podcasts)
                        .then().assertThat().body("data.id", hasItem(podcastId));
        }
}
