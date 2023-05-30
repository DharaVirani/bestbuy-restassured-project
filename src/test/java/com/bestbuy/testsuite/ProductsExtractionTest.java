package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021(){
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //22. Extract the total
    @Test
    public void test022(){
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String productName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 5th product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }
    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> listOfNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of product names are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");

    }
    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of List of Ids are : " + listOfIds.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }
    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Duracell - D Batteries (4-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price of product name 'Duracell - D Batteries (4-Pack)' is : " + model);
        System.out.println("------------------End of Test---------------------------");
    }
    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String, ?>> categories = response.extract().path("data.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories for 8th product are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<String> categories = response.extract().path("data.findAll{it.id='150115'}.find{it.categories}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions for all products are: " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }
    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<String> lisOfCategoriesId = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Id for all categories are: " + lisOfCategoriesId);
        System.out.println("------------------End of Test---------------------------");
    }
    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> productNames =  response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product names where type = HardGood are: " + productNames );
        System.out.println("------------------End of Test---------------------------");
    }
    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<HashMap<String,?>> categories =  response.extract().path("data.findAll{it.name='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.find{it.categories}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories for the product where name is Duracell - AA 1.5V CopperTop Batteries (4-Pack)  are: " + categories.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Products whose price < 5.49: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {
        List<String> names =  response.extract().path("data.findAll{it.name='Energizer - MAX Batteries AA (4-Pack)'}.find{it.categories.name}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack) are: " + names );
        System.out.println("------------------End of Test---------------------------");
    }
    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacturers = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products are: " + manufacturers);
        System.out.println("------------------End of Test---------------------------");
    }
    //38. Find the img of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<String> img = response.extract().path("data.findAll{it.manufacturer='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The img of products whose manufacturer is = Energizer are: " + img);
        System.out.println("------------------End of Test---------------------------");
    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.find{it.categories.createdAt}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Created at for all categories whose price > 5.99: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test040(){
        List<String> listOfUrls = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Urls are: " + listOfUrls);
        System.out.println("------------------End of Test---------------------------");

    }
}
