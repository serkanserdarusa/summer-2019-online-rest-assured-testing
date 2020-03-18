package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice11 {
    @BeforeAll
    public static void setup()
    {

        baseURI = ConfigurationReader.getProperty("spartan.uri");
    }


    @Test
    @DisplayName("post something in spartan")
    public void test(){


        File file=new File(System.getProperty("user.dir")+"/spartan.json");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(file)
                .post("/spartans/").prettyPrint();

    }
    @Test
    @DisplayName("delete something in spartans")
    public void test1(){



        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/spartans/{id}",104).prettyPrint();

    }
}
