package org.caravan.controller;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.services.TripsService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/trips")
public class TripsController {

  @Inject
  private TripsService tripsService;

  @GET
  public Response getTrips(@BeanParam TripsQueryParams queryParams) {
    return Response.ok(queryParams).build();
  }

  @POST
  @Transactional
  public TripResponse createTrip(@Valid CreateTripRequest request) {
    return tripsService.createTrip(request);
  }
}
