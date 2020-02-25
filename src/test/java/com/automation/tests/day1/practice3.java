package com.automation.tests.day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class practice3 {

    private String baseURI = "https://www.metaweather.com/api/";



    @Test
    public void test(){
        Response response=given().
        header("application/json","accept").
                queryParam("query","New").
                 get(baseURI+"location/search/");
    assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());

    }
    /**
     * /api/location/search/?query=san
     * /api/location/search/?query=london
     * /api/location/search/?lattlong=36.96,-122.02
     * /api/location/search/?lattlong=50.068,-5.316
     * /api/location/44418/ }/
     * "title": "San Francisco",
     * "location_type": "City",
     * "woeid": 2487956,
     * "latt_long": "37.777119, -122.41964"
     * },
     */


    @Test
    public void test8(){
        Response response=given().
                header("accept","application/json").
                pathParam("woeid","2459115").
                get(baseURI+"location/{woeid}");
        assertEquals(200,response.statusCode());
        System.out.println(response.prettyPrint());
        String body=response.getBody().asString();
        assertTrue(body.contains("New"));
        assertEquals("application/json",response.getHeader("Content-type"));
        System.out.println(response.getContentType());

    }
    @Test
    public void test1(){
        Response response=given().
                header("application/json","accept").
                pathParam("woeid","2487956").
                get(baseURI+"location/{woeid}");
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());

    }

    }









