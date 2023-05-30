package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //11. Verify the if the total is equal to 51957
    @Test
    public void test011(){
       response.body("total",equalTo(51958));
    }
    //12. Verify the if the stores of limit is equal to 10
    @Test
    public void test012(){
        response.body("limit",equalTo(10));
    }
    //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test013(){
        response.body("data.name",hasItem("Duracell - AA Batteries (8-Pack)"));
    }
    //14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
    //Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void test014(){
        response.body("data.name",hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)","Duracell - AA Batteries (8-Pack)","Energizer - MAX Batteries AA (4-Pack)"));
    }
    //15. Verify the productId=150115 inside categories of the third name is “Household Batteries” - PENDINGGGG
    @Test
    public void test005() {
        response

                .body("data[3]", hasEntry("id", 150115))
                .body("data[3].categories[2]", hasEntry("name", "Household Batteries"));
    }
    //16. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void test016(){
        response
                .body("data[8]", hasEntry("id", 333179))
                .body("data[8].categories[1]",hasEntry("name", "Housewares"));
    }


    //17. Verify the price = 4.99 of forth product
    @Test
    public void test017(){
        response.body("data[3].price", equalTo(4.99f) );
    }
    //18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    @Test
    public void test018(){
        response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));
    }
    //19. Verify the ProductId = 333179 for the 9th product
    @Test
    public void test019(){
        response.body("data[8].id",equalTo(333179));
    }
   // 20. Verify the prodctId = 346575 have 5 categories
    @Test
    public void test020(){
        response
                .body("data[9]",hasEntry("id", 346575))
                .body("data[9].categories", hasSize(5));
    }


}
