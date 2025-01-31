package org.caravan.controller;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.dto.UpdateTripRequest;
import org.caravan.services.TripsService;

import jakarta.annotation.security.RolesAllowed;
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
  @RolesAllowed("user")
  public List<TripResponse> getTrips(@BeanParam TripsQueryParams queryParams) {
    return tripsService.getTrips(queryParams);
  }

  @GET
  @Transactional
  @RolesAllowed("user")
  @Path("/{tripId}")
  public TripResponse getTripById(Long tripId) {
    return tripsService.getTripById(tripId);
  }

  @POST
  @Transactional
  @RolesAllowed("user")
  public TripResponse createTrip(@Valid CreateTripRequest request) {
    return tripsService.createTrip(request);
  }

  @PUT
  @Transactional
  @RolesAllowed("user")
  @Path("/{tripId}")
  public TripResponse updateTrip(Long tripId, @Valid UpdateTripRequest request) {
    return tripsService.updateTrip(tripId, request);
  }

  @DELETE
  @Transactional
  @RolesAllowed("user")
  @Path("/{tripId}")
  public void deleteTrip(Long tripId) {
    tripsService.deleteTrip(tripId);
  }
}
