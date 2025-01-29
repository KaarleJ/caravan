package org.caravan.controller;

import org.caravan.dto.TripsQueryParams;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/trips")
public class TripsController {

  @GET
  public Response getTrips(@BeanParam TripsQueryParams queryParams) {
    return Response.ok(queryParams).build();
  }
}
