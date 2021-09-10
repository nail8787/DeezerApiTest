package com.deezer.api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class deletePlaylistSteps {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.deezer.com")
            .setRelaxedHTTPSValidation()
            .build();

    @Когда("Удалить плейлист с идентификатором {biginteger}")
    public void deletePlaylistAttempt(BigInteger arg0){
//        String body = given().spec(requestSpecification)
//                .when().delete("/playlist/" + arg0.toString() + "&access_token=frKNR5APObxRP81PPPEhu6Cz7ALOtV0BgndlKmhnvXtplb1VbF").getBody().asString();
//        Assertions.assertEquals("true", body);
        given().spec(requestSpecification)
                .when().log().all().get("/search/user?name=raccoon8787");
    }

    @Тогда("Удаленный плейлист с идентификатором {string} больше не выдается в поиске")
    public void checkDeletedPlaylist(String arg0) {
        given().spec(requestSpecification)
                .when().get("/playlist/" + arg0.toString())
                .then().log().all().body("error.message", equalTo("no data"));
    }
}
