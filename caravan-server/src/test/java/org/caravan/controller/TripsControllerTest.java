package org.caravan.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TripsControllerTest {

  @Test
  public void testGetTripsNoAuth() {
    given()
        .when().get("/trips")
        .then()
        .statusCode(401);
  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testGetTrips() {
    given()
        .when().get("/trips")
        .then()
        .statusCode(200)
        .body("size()", greaterThan(0))
        .body("[0].name", equalTo("Tatooine Shenanigans"))
        .body("[0].description", equalTo("Visit Tatooine"))
        .body("[0].date", equalTo("2025-02-19"));

  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testCreateTrip() {
    given().log().everything()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Tatooine Shenanigans\",\"description\":\"Visit Tatooine\",\"date\":\"2025-02-19\"}")
        .when().post("/trips")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .body("name", equalTo("Tatooine Shenanigans"))
        .body("description", equalTo("Visit Tatooine"))
        .body("date", equalTo("2025-02-19"));
  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testGetTripById() {
    String tripId = given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Tatooine Shenanigans 2\",\"description\":\"Visit Tatooine again\", \"date\":\"2025-02-22\"}")
        .when().post("/trips")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .extract().path("id").toString();

    given()
        .when().get("/trips/" + tripId)
        .then()
        .statusCode(200)
        .body("id", equalTo(Integer.valueOf(tripId)));
  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testGetTripByIdNotFound() {
    given()
        .when().get("/trips/999999")
        .then()
        .statusCode(404);
  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testUpdateTrip() {
    String tripId = given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Tatooine Shenanigans 3\",\"description\":\"Visit Tatooine again\", \"date\":\"2025-02-22\"}")
        .when().post("/trips")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .extract().path("id").toString();

    given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Tatooine Shenanigans 4\",\"description\":\"Visit Tatooine again\", \"date\":\"2025-02-22\"}")
        .when().put("/trips/" + tripId)
        .then()
        .statusCode(200)
        .body("id", equalTo(Integer.valueOf(tripId)))
        .body("name", equalTo("Tatooine Shenanigans 4"));
  }

  @Test
  @TestSecurity(user = "test|1234")
  public void testDeleteTrip() {
    String tripId = given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Tatooine Shenanigans 5\",\"description\":\"Visit Tatooine again\", \"date\":\"2025-02-22\"}")
        .when().post("/trips")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .extract().path("id").toString();

    given()
        .when().delete("/trips/" + tripId)
        .then()
        .statusCode(204);

    given()
        .when().get("/trips/" + tripId)
        .then()
        .statusCode(404);
  }

  @Test
  @TestSecurity(user = "test|12345")
  public void testDeleteTripOfAnotherUser() {
    given()
        .when().delete("/trips/" + 1)
        .then()
        .statusCode(403);
  }

}