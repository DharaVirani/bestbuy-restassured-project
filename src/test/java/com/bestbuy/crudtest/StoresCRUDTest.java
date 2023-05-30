package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest {

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }




    static String name = "Priya" + TestUtils.getRandomValue();
    static String type = "SoftGood";
    static String address = "Flat 54, Hatton road, HA0 4AH";
    static String address2 = "Ghareli park";
    static String city = "Surat";
    static String state = "Gujarat";
    static String zip = "39002";
    static int lat = 10;
    static int lng = 5 ;
    static String hours = "24";
    static int id;

    @Test
    public void test001(){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(storePojo)
                .when()
                .post();
        id =response.then().extract().path("id");
        response.then().log().body();
    }

    @Test
    public void test002() {

        Response response= given()
                .when()
                .get("/" + id);
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .patch("/" + id);
        response.then().statusCode(200);
        response.then().log().body();
    }

    @Test
    public void test004() {
        given()
                .when()
                .delete("/" + id)
                .then()
                .statusCode(200);

    }
}
