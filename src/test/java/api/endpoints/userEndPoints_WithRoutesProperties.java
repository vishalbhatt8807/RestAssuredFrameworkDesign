package api.endpoints;
//Created for perform CRUD operation.

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class userEndPoints_WithRoutesProperties {

   static ResourceBundle getUrl(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("routes"); //Load the Properties file present in Test Resouces folder
        return resourceBundle;
    }
    public static Response createUser(User payload){
       String post_url = getUrl().getString("post_url");
        Response reponse = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return reponse;
    }

    public static Response getUser(String username){
        String get_url = getUrl().getString("get_url");
        Response reponse = given()
                .pathParams("username",username)
                .when()
                .get(get_url);
        return reponse;
    }

    public static Response updateUser(User payload, String username){
        String update_url = getUrl().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username",username)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }

    public static Response deleteUser(String username){
        String delete_url = getUrl().getString("delete_url");
        Response reponse = given()
                .pathParams("username",username)
                .when()
                .delete(delete_url);
        return reponse;
    }

}
