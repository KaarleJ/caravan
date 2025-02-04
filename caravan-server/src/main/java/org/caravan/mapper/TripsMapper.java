package org.caravan.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.caravan.dto.TripResponse;
import org.caravan.model.Trip;

public class TripsMapper {
  public static TripResponse toTripResponse(Trip trip) {
    return TripResponse.builder()
        .id(trip.getId())
        .name(trip.getName())
        .description(trip.getDescription())
        .date(trip.getDate())
        .status(trip.getStatus() != null ? trip.getStatus().name().toLowerCase() : null)
        .createdBy(UserMapper.toUserResponse(trip.getCreatedBy()))
        .participants(UserMapper.toUserResponseList(trip.getParticipants()))
        .build();
  }

  public static List<TripResponse> toTripResponses(List<Trip> trips) {
    return trips.stream()
        .map(TripsMapper::toTripResponse)
        .collect(Collectors.toList());
  }
}
