package org.caravan.mapper;

import org.caravan.dto.TripResponse;
import org.caravan.model.Trip;

public class TripsMapper {
  public static TripResponse toTripResponse(Trip trip) {
    return TripResponse.builder()
        .id(trip.getId())
        .name(trip.getName())
        .description(trip.getDescription())
        .date(trip.getDate())
        .createdById(trip.getCreatedBy().getId())
        .participants(UserMapper.toUserResponseList(trip.getParticipants()))
        .build();
  }
}
