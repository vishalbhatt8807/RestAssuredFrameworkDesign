package api.tests;

import api.endpoints.userEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String UserID,String UserName, String FirstName, String LastName,String Email, String Password, String Phone){
       User userPayload = new User();
        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setUsername(Password);
        userPayload.setPhone(Phone);

        Response response = userEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 2,dataProvider = "Usernames",dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username){
        Response response = userEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
