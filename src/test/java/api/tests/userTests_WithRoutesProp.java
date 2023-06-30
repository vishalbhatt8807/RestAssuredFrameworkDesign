package api.tests;

import api.endpoints.userEndPoints_WithRoutesProperties;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class userTests_WithRoutesProp {
    Faker faker = new Faker();
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setUsername(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logger
        logger = LogManager.getLogger(this.getClass());


    }

    @Test(priority = 1)
    public void testPostUser(){
       logger.info("*************Creating User**************");
        Response response =  userEndPoints_WithRoutesProperties.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*************User is Created**************");
    }

    @Test(priority = 2)
    public void testGetUser(){
        logger.info("*************User is Getting Data**************");
        Response response =  userEndPoints_WithRoutesProperties.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*************User got Data**************");
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        logger.info("*************User is Updating Data**************");
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        Response response =  userEndPoints_WithRoutesProperties.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);


        //Checking the data after updating data
        Response responseAfter =  userEndPoints_WithRoutesProperties.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfter.getStatusCode(),200);
        logger.info("*************User is Updated Data**************");
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        logger.info("*************User is Deleting **************");
        Response response =  userEndPoints_WithRoutesProperties.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*************User is Deleted**************");
    }
}
