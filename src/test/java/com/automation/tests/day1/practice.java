package com.automation.tests.day1;

        import com.google.gson.internal.bind.util.ISO8601Utils;
        import io.restassured.http.ContentType;
        import io.restassured.http.Header;
        import io.restassured.http.Headers;
        import io.restassured.response.Response;
        import io.restassured.response.ValidatableResponse;
        import org.junit.jupiter.api.Test;
        import org.w3c.dom.ls.LSOutput;
        import spark.utils.Assert;

        import static io.restassured.RestAssured.baseURI;
        import static io.restassured.RestAssured.given;
        import static org.junit.jupiter.api.Assertions.assertEquals;

public class practice {

    /**
     * given()
     * set cookies, add auth, adding parameters, setting header info
     * .when()
     * GET, POST, PUT,DELETE.. etc
     * .then()
     * Validate the status code, extract response, ectract headers, cookies, extract the response body
     */
    private  String baseURI="http://ec2-100-26-144-145.compute-1.amazonaws.com:1000/ords/hr";


    @Test
    public void test(){

      Response response=given().
                  get(baseURI+"/employees");
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPeek());


    }

    @Test
    public void test1(){

        Response response= given().
                header("accept","application/json").
                get(baseURI+"/employees/100");
        assertEquals(200,response.statusCode());
        System.out.println(response.prettyPrint());
    }
   @Test
           public void test2(){
    Response response=given().
           accept(ContentType.JSON).
           get(baseURI+"/departments");
       assertEquals(200,response.statusCode());
       System.out.println(response.prettyPrint());

}

@Test
    public void test3(){
        Response response=given().
                contentType(ContentType.JSON).
                get(baseURI+"/regions/4");
        assertEquals(200,response.statusCode());
    System.out.println(response.prettyPrint());

}

@Test
    public void test4(){
    Response response=given().
            contentType(ContentType.JSON).
            get(baseURI+"/regions");

   Headers header=response.getHeaders();
    System.out.println(response.getHeaders());

    for(Header h:header){
        System.out.println(h);
    }
}

}