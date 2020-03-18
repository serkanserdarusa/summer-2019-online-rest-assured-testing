package com.automation.tests.day1;


import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice12 {
    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("spartan.uri");
    }

    @Test
    @DisplayName("Save payload into java collection")
    public void test3() {
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get("/spartans").prettyPeek();

        List<Map<String,?>> collection1=response.jsonPath().get();//to get all list seperately

        System.out.println(collection1);

        for( Map<String,?> each:collection1){
            System.out.println(each);
        }
        Map<String, ?>collection = response.jsonPath().getMap("[0]",String.class,String.class);
        System.out.println(collection);//just get one collection


    }
}

