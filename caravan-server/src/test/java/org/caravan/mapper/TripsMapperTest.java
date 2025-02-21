package org.caravan.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.caravan.dto.TripResponse;
import org.caravan.model.Trip;
import org.caravan.model.User;
import org.caravan.model.enums.TripStatus;
import org.junit.jupiter.api.Test;

public class TripsMapperTest {

  @Test
  public void testToTripResponse() {
    User user = User.builder()
        .id("user1")
        .email("user1@test.com")
        .build();

    Trip trip = Trip.builder()
        .id(1L)
        .name("Test Trip")
        .description("This is a test trip.")
        .date(LocalDate.of(2025, 2, 19))
        .status(TripStatus.UPCOMING)
        .createdBy(user)
        .participants(Collections.emptyList())
        .build();

    TripResponse response = TripsMapper.toTripResponse(trip);

    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals("Test Trip", response.getName());
    assertEquals("This is a test trip.", response.getDescription());
    assertEquals(LocalDate.of(2025, 2, 19), response.getDate());

    assertEquals("upcoming", response.getStatus());
  
    assertNotNull(response.getCreatedBy());
    assertEquals("user1", response.getCreatedBy().getId());

    
    assertNotNull(response.getParticipants());
    assertTrue(response.getParticipants().isEmpty());
  }

  @Test
  public void testToTripResponses() {
    User user = User.builder().id("user1").email("user1@test.com").build();

    Trip trip1 = Trip.builder()
        .id(1L)
        .name("Trip 1")
        .description("First trip")
        .date(LocalDate.of(2025, 2, 19))
        .status(TripStatus.UPCOMING)
        .createdBy(user)
        .participants(Collections.emptyList())
        .build();

    Trip trip2 = Trip.builder()
        .id(2L)
        .name("Trip 2")
        .description("Second trip")
        .date(LocalDate.of(2025, 2, 20))
        .status(null)
        .createdBy(user)
        .participants(Collections.emptyList())
        .build();

    List<TripResponse> responses = TripsMapper.toTripResponses(List.of(trip1, trip2));
    assertNotNull(responses);
    assertEquals(2, responses.size());

    TripResponse response1 = responses.get(0);
    assertEquals(1L, response1.getId());
    assertEquals("Trip 1", response1.getName());
    assertEquals("First trip", response1.getDescription());
    assertEquals("upcoming", response1.getStatus());

    TripResponse response2 = responses.get(1);
    assertEquals(2L, response2.getId());
    assertEquals("Trip 2", response2.getName());
    assertEquals("Second trip", response2.getDescription());
    assertNull(response2.getStatus());
  }
}