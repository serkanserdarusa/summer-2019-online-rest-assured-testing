package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spark.utils.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class practice5 {
    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    /**
     * Given accept type is JSON
     * When users sends a GET request to "/employees"
     * Then status code is 200
     * And Content type is application/json
     * And response time is less than 3 seconds
     */
    @Test
    @DisplayName("verify the response time less than 3 second")


    public void test1() {
        given().
                accept(ContentType.JSON).
                when().
                get("/employees").
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                time(lessThan(10L), TimeUnit.SECONDS).
                log().all(true);//payload=body, and our body has JSON format
    }


    /*{{baseUrl}}/countries?q={"country_id":"US"}

    Given accept type is JSON
    And parameters: q = {"country_id":"US"}
    When users sends a GET request to "/countries"
    Then status code is 200
    And Content type is application/json
    And country_name from payload is "United States of America"

     */
    @Test
    @DisplayName("verify country name")
    public void test22() {
        given().
                contentType(ContentType.JSON).
                queryParam("q", "{\"country_id\":\"US\"}").
                when().
                get("/countries").
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("items[0].country_name", is("United States of America")).log().all(true);


    }


    /**
     * given path parameter is "/countries" and region id is 2
     * when user makes get request
     * then assert that status code is 200
     * and user verifies that body returns following country names
     * |Argentina                |
     * |Brazil                   |
     * |Canada                   |
     * |Mexico                   |
     * |United States of America |
     */
    @Test
    @DisplayName("Verify that payload contains following countries")
    public void test3() {
        //to use List.of() set java 9 at least


        List<String> expected = new ArrayList<>(Arrays.asList("Argentina", "Brazil", "Canada", "Mexico", "United States of America"));

        Response response = given().
                contentType(ContentType.JSON).
                queryParam("q", "{\"region_id\":\"2\"}").
                when().
                get("/countries").prettyPeek();
        List<String> actual = response.jsonPath().getList("items.country_name");

        assertEquals(expected, actual);
    }


    /**
     * given path parameter is "/employees"
     * when user makes get request
     * then assert that status code is 200
     * Then user verifies that every employee has positive salary
     *
     */

    @Test
    @DisplayName("verify salary bigger than 0")
    public void test21(){
        given().
                contentType(ContentType.JSON).
                when().
                get("/employees").
                then().assertThat().
                statusCode(200).body("items.employee_id",everyItem(greaterThan(90))).log().all(true);


    }

            @Test
            @DisplayName("Verify that every employee has positive salary")
            public void test4() {
                given().
                        accept(ContentType.JSON).
                        when().
                        get("/employees").
                        then().
                        assertThat().
                        statusCode(200).
                        body("items.salary", everyItem(greaterThan(0))).log().all();

            }
                /**
                 * given path parameter is "/employees/{id}"
                 * and path parameter is 101
                 * when user makes get request
                 * then assert that status code is 200
                 * and verifies that phone number is 515-123-4568
                 *
                 */

                @Test
                @DisplayName("verify empployee_id 101 and phone number:515-123-4568")
                public void test23(){
                   Response response= given().
                            contentType(ContentType.JSON).
                           pathParam("id",101).

                                    when().
                            get("/employees/{id}");
                   assertEquals(200,response.getStatusCode());
                   String a="515.123.4568";
                   String actually=response.jsonPath().get("phone_number");
                    assertEquals(a,actually,"something wrong");


                }



                @Test
                @DisplayName("Verify that employee 101 has following phone number: 515-123-4568")
                public void test5 () {
                    Response response = given().
                            accept(ContentType.JSON).
                            when().
                            get("/employees/{id}", 101);
                    assertEquals(200, response.getStatusCode());

                    String expected = "515-123-4568";
                    String actual = response.jsonPath().get("phone_number");

                    expected = expected.replace("-", ".");
                    assertEquals(expected, actual);
                }

                /**
                 * given path parameter is "/employees"
                 * when user makes get request
                 * then assert that status code is 200
                 * and verify that body returns following salary information after sorting from higher to lower
                 *  24000, 17000, 17000, 12008, 11000,
                 *  9000, 9000, 8200, 8200, 8000,
                 *  7900, 7800, 7700, 6900, 6500,
                 *  6000, 5800, 4800, 4800, 4200,
                 *  3100, 2900, 2800, 2600, 2500
                 *
                 */
















                @Test
                @DisplayName("verify that body returns following salary information after sorting from higher to lower(after sorting it in descending order)")
                public void test6 () {
                    List<Integer> expectedSalaries = Arrays.asList(24000, 17000, 17000, 12008, 11000,
                            9000, 9000, 8200, 8200, 8000,
                            7900, 7800, 7700, 6900, 6500,
                            6000, 5800, 4800, 4800, 4200,
                            3100, 2900, 2800, 2600, 2500);
                    Response response = given().
                            accept(ContentType.JSON).
                            when().
                            get("/employees");
                    assertEquals(200, response.statusCode());

                    List<Integer> actualSalaries = response.jsonPath().getList("items.salary");

                    Collections.sort(actualSalaries, Collections.reverseOrder());

                    System.out.println(actualSalaries);

                    assertEquals(expectedSalaries, actualSalaries, "Salaries are not matching");
                }

                /** ####TASK#####
                 *  Given accept type as JSON
                 *  And path parameter is id with value 2900
                 *  When user sends get request to /locations/{id}
                 *  Then user verifies that status code is 200
                 *  And user verifies following json path contains following entries:
                 *      |street_address         |city  |postal_code|country_id|state_province|
                 *      |20 Rue des Corps-Saints|Geneva|1730       |CH        |Geneve        |
                 *
                 */

                @Test
                @DisplayName("Verify that JSON body has following entries")
                public void test7 () {
                    given().
                            accept(ContentType.JSON).
                            pathParam("id", 2900).
                            when().
                            get("/locations/{id}").
                            then().
                            assertThat().
                            statusCode(200).
                            body("", hasEntry("street_address", "20 Rue des Corps-Saints")).
                            body("", hasEntry("city", "Geneva")).
                            body("", hasEntry("postal_code", "1730")).
                            body("", hasEntry("country_id", "CH")).
                            body("", hasEntry("state_province", "Geneve")).
                            log().all(true);

                }


    /**
                 *     "location_id": 2900,
                 *     "street_address": "20 Rue des Corps-Saints",
                 *     "postal_code": "1730",
                 *     "city": "Geneva",
                 *     "state_province": "Geneve",
                 *     "country_id": "CH",
                 */
                @Test
                @DisplayName("Verify that JSON body has following entries")
                public void test8 () {
                    given().
                            accept(ContentType.JSON).
                            pathParam("id", 2900).
                            when().
                            get("/locations/{id}").
                            then().
                            assertThat().
                            statusCode(200).
                            body("street_address", is("20 Rue des Corps-Saints")).
                            body("city", is("Geneva")).
                            body("postal_code", is("1730")).
                            body("country_id", is("CH")).
                            body("state_province", is("Geneve")).
                            log().all(true);
                }
            }
