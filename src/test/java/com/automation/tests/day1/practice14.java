package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice14 {
    @BeforeAll
    public static void setup(){


        baseURI = ConfigurationReader.getProperty("meta.weather.uri");

}

@Test
    public void test(){
        Response response=given()
                .contentType(ContentType.JSON)
                .pathParam("woeid","2459115")
                .get(baseURI+"/location/{woeid}");
    System.out.println(response.prettyPrint());

}
 }