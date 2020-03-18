package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class practice13 {

  private String baseURI="https://api.openrates.io/";

    @Test
    public void test(){
    Response response=given()
            .contentType(ContentType.JSON)
            .get(baseURI+"latest");
    assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());


        // https://api.exchangeratesapi.io/latest?base=USD
}
@Test
    public void test2(){

        Response response=given()
                .contentType(ContentType.JSON)
                .queryParam("base","TRY")
                .get(baseURI+"latest");
        assertEquals(200,response.getStatusCode());
    System.out.println(response.prettyPrint());


}



}