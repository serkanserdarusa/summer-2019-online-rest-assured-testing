package com.automation.tests.day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class practice2 {

    private String baseURI = "https://api.openrates.io/";
    // https://api.exchangeratesapi.io/latest?base=USD

    @Test
    public void test() {
        Response response = given().
                contentType(ContentType.JSON).
                get(baseURI + "latest");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());


    }

    @Test
    public void test1() {


        Response response = given().
                contentType(ContentType.JSON).
                queryParam("base", "TRY").
                 get(baseURI + "latest");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
    @Test
    public void test2() {


        Response response = given().
                contentType(ContentType.JSON).
                queryParam("base", "TRY").
                get(baseURI + "latest");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
    }
//GET https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01&base=USD&symbols=ILS,JPY

    @Test
    public void test3(){

        Response response=given().
                accept(ContentType.JSON).
                queryParam("start_at","2014-01-01").
                queryParam("end_at","2019-09-01").
                queryParam("base","USD").
                queryParam("symbols","TRY").
                get(baseURI+"history");

        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void test4(){
        Response response=
                given().
                        contentType(ContentType.JSON).
                        queryParam("base","USD").
                        get(baseURI+"latest");
        assertEquals(200,response.statusCode());
        String body=response.getBody().asString();
        assertTrue(body.contains("\"base\":\"USD\""));

        System.out.println(response.prettyPrint());
        assertEquals("application/json",response.getHeader("Content-type"));
        assertEquals("application/json",response.header("Content-type"));
        assertEquals("application/json",response.contentType());

        System.out.println(response.getHeader("Content-type"));
        System.out.println(response.header("Content-Type"));
        System.out.println(response.getContentType());

    }
    }
