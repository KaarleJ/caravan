package org.caravan.services;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.dto.UpdateTripRequest;


public interface TripsService {
  public List<TripResponse> getTrips(TripsQueryParams queryParams);

  public TripResponse createTrip(CreateTripRequest request);

  public TripResponse getTripById(Long tripId);

  public TripResponse updateTrip(Long tripId, UpdateTripRequest request);

  public void deleteTrip(Long tripId);
}
