package org.caravan.services;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;

public interface TripsService {
  public List<TripResponse> getTrips(TripsQueryParams queryParams);

  public TripResponse createTrip(CreateTripRequest request);
}
