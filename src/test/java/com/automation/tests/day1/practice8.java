package com.automation.tests.day1;

import com.automation.pojos.Address;
import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class practice8 {


    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    @DisplayName("get some job info from Pojo to Json")//serialization

    public void test1() {

        Job job = new Job("1", "Sdet", 4000, 5000);
        Gson gson = new Gson();
        String json = gson.toJson(job);
        System.out.println(json);


    }

    @Test
    @DisplayName("get some job info from Pojo to Json")//serialization

    public void test2() {
        Location locations = new Location(2, "11075 regal forest", "30024", "suwanee", "ga", "usa");
        Gson gson = new Gson();
        String json = gson.toJson(locations);
        System.out.println(json);

    }


    @Test
    @DisplayName("get some job info from Pojo to Json")//serialization

    public void test3() {
        Address address = new Address(3,"suwanee", "ga", "11075 regal forest", 30024);
      Gson gson=new Gson();
      String json=gson.toJson(address);
        System.out.println(json);
    }
}
