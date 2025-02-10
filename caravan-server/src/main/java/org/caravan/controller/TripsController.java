package org.caravan.controller;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.dto.UpdateTripRequest;
import org.caravan.services.TripsService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Path("/trips")
public class TripsController {

  @Inject
  private TripsService tripsService;

  @GET
  @Transactional
  @Authenticated
  public List<TripResponse> getTrips(@BeanParam TripsQueryParams queryParams) {
    return tripsService.getTrips(queryParams);
  }

  @GET
  @Transactional
  @Authenticated
  @Path("/{tripId}")
  public TripResponse getTripById(Long tripId) {
    return tripsService.getTripById(tripId);
  }

  @POST
  @Transactional
  @Authenticated
  public TripResponse createTrip(@Valid CreateTripRequest request) {
    return tripsService.createTrip(request);
  }

  @PUT
  @Transactional
  @Authenticated
  @Path("/{tripId}")
  public TripResponse updateTrip(Long tripId, @Valid UpdateTripRequest request) {
    return tripsService.updateTrip(tripId, request);
  }

  @DELETE
  @Transactional
  @Authenticated
  @Path("/{tripId}")
  public void deleteTrip(Long tripId) {
    tripsService.deleteTrip(tripId);
  }

  @GET
  @Authenticated
  @Path("/ping")
  public String ping() {
    return "pong";
  }
}
