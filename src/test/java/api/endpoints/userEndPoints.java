package api.endpoints;
//Created for perform CRUD operation.

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class userEndPoints {

    public static Response createUser(User payload){
        Response reponse = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
        return reponse;
    }

    public static Response getUser(String username){
        Response reponse = given()
                .pathParams("username",username)
                .when()
                .get(Routes.get_url);
        return reponse;
    }

    public static Response updateUser(User payload, String username){
        Response reponse = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username",username)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return reponse;
    }

    public static Response deleteUser(String username){
        Response reponse = given()
                .pathParams("username",username)
                .when()
                .delete(Routes.delete_url);
        return reponse;
    }

}
