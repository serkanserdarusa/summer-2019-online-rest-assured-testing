package com.automation.tests.day1;

import com.automation.pojos.Company;
import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.pojos.Regions;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class practice7 {

    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    @DisplayName("get some job info from Json to POJO")//Deserialization

    public void test1() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/jobs");

        System.out.println(response.prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        Job job = jsonPath.getObject("items", Job.class);

        System.out.println(job);
        System.out.println(job.getJobId());
        System.out.println(job.getMax_salary());


    }


    @Test
    @DisplayName("verify in location from json to Pojo")
    public void test2() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/locations");
        System.out.println(response.prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        Location location = jsonPath.getObject("items[0]", Location.class);

        System.out.println(location.getCity());
        System.out.println(location.getStreetAddress());
        System.out.println(location.getPostalCode());

    }

    @Test
    @DisplayName("verify company json to pojo")
    public void test3() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/regions");

        System.out.println(response.prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        Regions regions = jsonPath.getObject("items[0]", Regions.class);

        System.out.println(regions.getRegionId());
        System.out.println(regions.getRegion_name());
    }
}
