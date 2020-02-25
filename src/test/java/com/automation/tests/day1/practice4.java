package com.automation.tests.day1;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class practice4 {


    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    public void test1() {
        given().
                accept(ContentType.JSON).
                when().
                get(baseURI + "/employees").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).log().all(true);

    }


    @Test
    public void test2() {
        given().
                accept(ContentType.JSON).
                pathParam("id", 100).
                when().
                get(baseURI + "/employees/{id}").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).log().body(true);

    }

    @Test
    public void test3() {
        given().
                accept(ContentType.JSON).
                pathParam("id", 100).
                when().
                get(baseURI + "/employees/{id}").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).and().assertThat().body("employee_id",is(100),
                "department_id",is(90),"first_name",is("Steven")).log().all(true);

    }
    /**
     * given path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe with is
     * use time less than 10L seconds
     */


    @Test
    public void test4(){

        given().
                contentType(ContentType.JSON).
        when().
                pathParam("id",1).
                get(baseURI+"/regions/{id}").
                then().
                assertThat().contentType(ContentType.JSON).and().statusCode(200).
                assertThat().body("region_name",is("Europe")).time(lessThan(10L),TimeUnit.SECONDS).log().all(true);

    }

    @Test
    public void test8(){
    JsonPath json=given().
            contentType(ContentType.JSON).
            when().
            get(baseURI+"/employees").thenReturn().jsonPath();
//        System.out.println(json.getString("items[0].last_name"));
//        System.out.println(json.getString("items[1].first_name"));
//        System.out.println(json.getString("items[-1].salary"));

       List<?>ss=json.getList("items.salary");
//        for( int i=0;i<ss.size();i++){
//            System.out.println(ss.get(i));
//        }
       for( Object s:ss)
        System.out.println(s);
    }

    @Test
    public void test18() {
        JsonPath json = given().
                contentType(ContentType.JSON).
                when().
                get(baseURI + "/employees").thenReturn().jsonPath();

        Map<String,?>sss=json.get("items[0]");
        System.out.println(sss);

        for(Map.Entry<String, ?> s:sss.entrySet()){
            System.out.println(s);
            System.out.println("key: "+s.getKey()+" value: "+s.getValue());
        }


    }

    @Test
    public void test21(){

        JsonPath count=given().
                contentType(ContentType.JSON).
                when().
                get("/countries").thenReturn().jsonPath();

        System.out.println(count.prettyPrint());
        System.out.println(count.getString("items[0].country_id"));
        System.out.println(count.getString("items[-1].country_id"));
        System.out.println(count.getString("items[0].country_name"));
        System.out.println(count.getString("items[4].region_id"));

        List<String>s=count.getList("items.country_name");
        System.out.println(s);
        for(int i=0;i<s.size();i++){
            System.out.println(s.get(i));
        }
        for(String k:s){
            System.out.println(k);
        }
    Map<?,?>l=count.get("items[4]");
            System.out.println(l);


   for(Map.Entry<?,?>h:l.entrySet()){
            System.out.println("key: "+h.getKey()+" value: "+h.getValue());

        }






    }

    @Test
    public void test22(){

        List<String>kk=given().
                contentType(ContentType.JSON).
                when().
                get(baseURI+"/employees").thenReturn().jsonPath().getList("items.salary");

        System.out.println(kk);
        System.out.println("=============");
        Collections.sort(kk);
        System.out.println(kk);
        System.out.println("=============");
        Collections.sort(kk,Collections.reverseOrder());
        System.out.println(kk);

    }
    @Test
    public void test5(){

        JsonPath json=given().
                contentType(ContentType.JSON).
                when().
                get("/employees").
                thenReturn().jsonPath();
        //System.out.println(json.prettyPrint());
        System.out.println(json.getString("items[0].first_name"));
        System.out.println(json.getString("items[-1].first_name"));

        List<String> ls=json.get("items.last_name");
        System.out.println(ls);
        List<String>ls1=json.get("items.first_name");
        System.out.println(ls1);
       for(String s:ls1){
           System.out.println(s);
       }
        Map<String,?>mp=json.get("items[0]");
        System.out.println(mp);
        Map<String,?>mp1=json.get("items[1]");
        System.out.println(mp1);
        for(Map.Entry<String,?>mpp:mp.entrySet()){
            System.out.println("Key:"+mpp.getKey()+"value:"+mpp.getValue());
        }

    }
    @Test
    public void test6(){
        JsonPath json=given().
                contentType(ContentType.JSON).
                when().
                get("/countries").thenReturn().jsonPath();
        //System.out.println(json.prettyPrint());
        System.out.println(json.getString("items[0].country_name"));
        System.out.println(json.getString("items[-1].country_name"));
        List<String>ls=json.get("items.region_id");
        System.out.println(ls);
        Map<String,?>m=json.get("items[0]");
        System.out.println(m);
        for(Map.Entry<String,?>k:m.entrySet()){
            System.out.println("key:"+k.getKey()+"value:"+k.getValue());
        }


    }
    @Test
    public void test7(){

        List<String> json=given().accept("application/json").
                when().get("/employees").thenReturn().jsonPath().get("items.salary");

        System.out.println(json);
        Collections.sort(json,Collections.reverseOrder());
        System.out.println(json);


    }



}
