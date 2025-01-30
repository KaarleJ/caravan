package org.caravan.services;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.model.User;


public interface TripsService {
  public List<TripResponse> getTrips(TripsQueryParams queryParams);

  public TripResponse createTrip(CreateTripRequest request, User creator);
}
