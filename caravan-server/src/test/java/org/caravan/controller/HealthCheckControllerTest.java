package org.caravan.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HealthCheckControllerTest {
  @Test
  void testHealthEndpoint() {
    given()
        .when().get("/health")
        .then()
        .statusCode(200)
        .body(is("OK"));
  }

}