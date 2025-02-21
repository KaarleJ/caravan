package org.caravan.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.caravan.dto.CreateUserRequest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
class UserControllerTest {

  @Test
  void testCreateUser() {
    CreateUserRequest request = CreateUserRequest.builder()
        .id("1")
        .email("tester@test.com")
        .profilePicture("https://www.test.com")
        .build();

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/users")
        .then()
        .statusCode(200);
  }

  @Test
  void testCreateUserReturns204() {
    CreateUserRequest request = CreateUserRequest.builder()
        .id("test|12346")
        .email("test@tester.com")
        .build();

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/users")
        .then()
        .statusCode(204);
  }
}