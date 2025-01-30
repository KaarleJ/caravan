package org.caravan.services;

import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.TripsQueryParams;
import org.caravan.mapper.TripsMapper;
import org.caravan.model.Trip;
import org.caravan.model.User;
import org.caravan.repository.TripsRepository;
import org.caravan.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsServiceImpl implements TripsService {

  @Inject
  private TripsRepository tripsRepository;

  @Inject UserRepository userRepository;

  public List<TripResponse> getTrips(TripsQueryParams queryParams) {
    return null;
  }

  public TripResponse createTrip(CreateTripRequest request) {
    User creator = userRepository.findById(request.getCreatedById());
    Trip trip = Trip.builder()
        .name(request.getName())
        .description(request.getDescription())
        .createdBy(creator)
        .participants(List.of(creator))
        .build();

    tripsRepository.persist(trip);

    return TripsMapper.toTripResponse(trip);
  }
}
