package com.automation.tests.day1;

import com.automation.pojos.Job;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice9 {
    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    @DisplayName("Get job info from JSON and convert it into POJO")
    public void test1() {
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .get("/jobs");
        System.out.println(response.prettyPrint());
        JsonPath jsonPath=response.jsonPath();
        List<Job> job=jsonPath.getList("items",Job.class);
        System.out.println(job);

    }
}
