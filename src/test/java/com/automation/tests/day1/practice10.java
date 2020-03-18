package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice10 {
    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

//    @Override
//    public String toString() {
//        return "SchoolTests{}";
//    }


    @Test
    @DisplayName("Create and Delete student")
    public void test1() {
        String studentbody = "{\n" +
                "  \"admissionNo\": \"1234\",\n" +
                "  \"batch\": 12,\n" +
                "  \"birthDate\": \"01/01/1890\",\n" +
                "  \"company\": {\n" +
                "    \"address\": {\n" +
                "      \"city\": \"McLean\",\n" +
                "      \"state\": \"Virginia\",\n" +
                "      \"street\": \"7925 Jones Branch Dr\",\n" +
                "      \"zipCode\": 22102\n" +
                "    },\n" +
                "    \"companyName\": \"Cybertek\",\n" +
                "    \"startDate\": \"02/02/2020\",\n" +
                "    \"title\": \"SDET\"\n" +
                "  },\n" +
                "  \"contact\": {\n" +
                "    \"emailAddress\": \"james_bond@gmail.com\",\n" +
                "    \"phone\": \"240-123-1231\",\n" +
                "    \"premanentAddress\": \"7925 Jones Branch Dr\"\n" +
                "  },\n" +
                "  \"firstName\": \"Serkan\",\n" +
                "  \"gender\": \"Males\",\n" +
                "  \"joinDate\": \"01/01/3321\",\n" +
                "  \"lastName\": \"Bond\",\n" +
                "  \"major\": \"CS\",\n" +
                "  \"password\": \"1234\",\n" +
                "  \"section\": \"101010\",\n" +
                "  \"subject\": \"Math\"\n" +
                "}";
        //add student
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(studentbody)
                .post("student/create").prettyPeek();

        //delete student
        Response response2=given()
                .contentType(ContentType.JSON)
                .when()
                .delete("student/delete/{id}",5657).prettyPeek();

    }

    @Test
    @DisplayName("Create and Delete student")
    public void test11() {

        File file= new File(System.getProperty("user.dir")+"/student.json");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(file)
                .post("student/create").prettyPrint();

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("student/delete/{id}",5709)
                .prettyPrint();



    }

}
