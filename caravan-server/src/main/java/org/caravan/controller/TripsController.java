package org.caravan.controller;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.model.User;
import org.caravan.security.UserContext;
import org.caravan.services.TripsService;

import jakarta.annotation.security.RolesAllowed;
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
  
  @Inject
  private UserContext userContext;

  @GET
  @Transactional
  @RolesAllowed("user")
  public Response getTrips(@BeanParam TripsQueryParams queryParams) {
    return Response.ok(queryParams).build();
  }

  @POST
  @Transactional
  @RolesAllowed("user")
  public TripResponse createTrip(@Valid CreateTripRequest request) {
    User currentUser = userContext.getCurrentUser();
    return tripsService.createTrip(request, currentUser);
  }
}
