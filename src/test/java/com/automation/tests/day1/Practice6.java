package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice6 {

    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("meta.weather.uri");
    }

    /**
     * TASK
     * Given accept type is JSON
     * When users sends a GET request to "/search"
     * And query parameter is 'New'
     * Then user verifies that payload contains 5 objects
     */

    @Test
    @DisplayName("verify body has 5 objects")
    public void test11(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("query","New")
                .when()
                .get("/search")
                .then()
                .assertThat()
                .statusCode(200)
                .body("",hasSize(5)).log().body(true);

    }




    @Test
    @DisplayName("Verify that are 5 cities that are matching 'New'")
    public void test1() {
        given().
                accept(ContentType.JSON).
                queryParam("query", "New").
                when().
                get("/search").
                then().
                assertThat().
                statusCode(200).
                body("", hasSize(5)).
                log().body(true);

    }


    /**
     * TASK
     * Given accept type is JSON
     * When users sends a GET request to "/search"
     * And query parameter is New
     * Then user verifies that 1st object has following info:
     * |title   |location_type|woeid  |latt_long          |
     * |New York|City         |2459115|40.71455,-74.007118|
     */

    @Test
    @DisplayName("Verifies that 1st city has following info:New York, City, 2459115, 40.71455,-74.007118")
    public void test22() {

        given()
                .contentType(ContentType.JSON)
                .queryParam("query","new")
                .when()
                .get("/search")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title[1]",is("New Delhi"))
                .body("location_type[1]",is("City"))
                .body("woeid[1]",is(28743736))
                .body("latt_long[1]",is("28.643999,77.091003")).log().body(true);


    }
    @Test
    @DisplayName("Verifies that 1st city has following info:New York, City, 2459115, 40.71455,-74.007118")
    public void test222() {

        given()
                .contentType(ContentType.JSON)
                .queryParam("query", "new")
                .when()
                .get("/search")
                .then()
                .assertThat()
                .statusCode(200)
                .body("", hasEntry("title[0]", "New Yok"))
                .body("", hasEntry("location_type[0]", "City"))
                .body("", hasEntry("woeid[0]", 2459115))
                .body("", hasEntry("latt_long[0]", "240.71455,-74.007118")).log().body(true);
    }

    //has entry work until if you wrote all path
    @Test
    @DisplayName("Verifies that 1st city has following info:New York, City, 2459115, 40.71455,-74.007118")
    public void test2() {
        given().
                accept(ContentType.JSON).
                queryParam("query", "New").
                when().
                get("/search").
                then().
                assertThat().
                statusCode(200).
                body("title[0]", is("New York")).
                body("location_type[0]", is("City")).
                body("woeid[0]", is(2459115)).
                body("latt_long[0]", is("40.71455,-74.007118")).
                log().body(true);
    }

    @Test
    @DisplayName("Verifies that 1st city has following info:New York, City, 2459115, 40.71455,-74.007118")
    public void test2_2() {
        Map<String, String> expected = new HashMap<>();
        expected.put("title", "New York");
        expected.put("location_type", "City");
        expected.put("woeid", "2459115");
        expected.put("latt_long", "40.71455,-74.007118");

        Response response = given().
                accept(ContentType.JSON).
                queryParam("query", "New").
                when().
                get("/search");
        JsonPath jsonPath = response.jsonPath();
        //String.class, String.class will force jsonpath to return map with String as key and value
        assertEquals(expected, jsonPath.getMap("[0]", String.class, String.class));
        //for first title, title[0], but for first object, we can say just [0]
        //if one object is a key=value pair like map, collection of this objects can be represented as list of map
        List<Map<String, ?>> values = jsonPath.get();
        for (Map<String, ?> value : values) {
            System.out.println(value);
        }
    }

    /* * TASK
     * Given accept type is JSON
     * When users sends a GET request to "/search"
     * And query parameter is 'Las'
     * Then user verifies that payload  contains following titles:
     * |Glasgow  |
     * |  Dallas |
     * |Las Vegas|*/

    @Test
    public void test3() {
        given().
                accept(ContentType.JSON).
                queryParam("query", "Las").
                when().
                get("/search").
                then().assertThat().body("title", hasItems("Glasgow", "Dallas", "Las Vegas")).log().body(true);
        //hasItems - exact match
        //contains- partial match
    }

     //            * TASK
    //             * Given accept type is JSON
    //             * When users sends a GET request to "/search"
    //             * And query parameter is 'Las'
    //             * Then verify that every item in payload has location_type City
    @Test
    @DisplayName("Verify that every item in payload has location_type City")
    public void test4() {
        given().
                accept(ContentType.JSON).
                queryParam("query", "Las").
                when().
                get("/search").
                then().assertThat().body("location_type", everyItem(is("City"))).
                log().all(true);

    }

    @Test
    @DisplayName("Verify following that payload contains weather forecast sources")
    public void test5() {
        List<String> expected = Arrays.asList("BBC", "Forecast.io", "HAMweather", "Met Office",
                "OpenWeatherMap", "Weather Underground",
                "World Weather Online");
        Response response = given().
                accept(ContentType.JSON).
                pathParam("woeid", 44418).
                when().
                get("/location/{woeid}");
        System.out.println(response.prettyPrint());
        List<String> actual = response.jsonPath().getList("sources.title");
        System.out.println(actual);
        assertEquals(expected, actual);

    }

}