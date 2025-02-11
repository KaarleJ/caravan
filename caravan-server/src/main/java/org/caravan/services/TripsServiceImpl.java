package org.caravan.services;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.dto.UpdateTripRequest;
import org.caravan.mapper.TripsMapper;
import org.caravan.model.Trip;
import org.caravan.model.User;
import org.caravan.model.enums.TripStatus;
import org.caravan.repository.TripsRepository;
import org.caravan.security.UserContext;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TripsServiceImpl implements TripsService {

  @Inject
  private TripsRepository tripsRepository;

  @Inject
  private UserContext userContext;

  public List<TripResponse> getTrips(TripsQueryParams queryParams) {
    User user = userContext.getCurrentUser();
    List<Trip> trips = tripsRepository.findTrips(user, queryParams);
    return TripsMapper.toTripResponses(trips);
  }

  public TripResponse createTrip(CreateTripRequest request) {
    User creator = userContext.getCurrentUser();

    Trip trip = Trip.builder()
        .name(request.getName())
        .description(request.getDescription())
        .date(request.getDate())
        .status(TripStatus.UPCOMING)
        .createdBy(creator)
        .participants(List.of(creator))
        .build();

    tripsRepository.persist(trip);
    return TripsMapper.toTripResponse(trip);
  }

  public TripResponse getTripById(Long tripId) {
    Trip trip = tripsRepository.findById(tripId);

    if (trip == null) {
      throw new NotFoundException("Trip not found");
    }

    userContext.validateOwnerShip(trip.getCreatedBy().getId());
    return TripsMapper.toTripResponse(trip);
  }

  public TripResponse updateTrip(Long tripId, UpdateTripRequest request) {
    Trip trip = tripsRepository.findById(tripId);

    if (trip == null) {
      throw new NotFoundException("Trip not found");
    }

    userContext.validateOwnerShip(trip.getCreatedBy().getId());
    trip.setName(request.getName());
    trip.setDescription(request.getDescription());
    trip.setDate(request.getDate());
    tripsRepository.persist(trip);
    return null;
  }

  public void deleteTrip(Long tripId) {
    Trip trip = tripsRepository.findById(tripId);

    if (trip == null) {
      throw new NotFoundException("Trip not found");
    }

    userContext.validateOwnerShip(trip.getCreatedBy().getId());
    tripsRepository.delete(trip);
  }
}
