package com.krigersv;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class ReqresTests extends TestBase {

    @Test
    void createUsersTest() {
        String authData = "{ \"name\": \"morpheus\",  \"job\": \"leader\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .contentType(JSON)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }


    @Test
    void successfulRegisterTest() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\",  \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void unsuccessfullyRegisterTest() {
        String authData = "{ \"email\": \"sydney@fife\",  \"password\": \"\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    void singleUserTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void userDeleteTest() {
        given()
                .log().uri()
                .delete("/api/users/2")
                .then()
                .log().status()
                .statusCode(204);

        }

    }