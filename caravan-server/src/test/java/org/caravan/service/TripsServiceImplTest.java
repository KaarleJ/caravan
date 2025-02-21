package org.caravan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.caravan.dto.CreateTripRequest;
import org.caravan.dto.TripResponse;
import org.caravan.dto.UpdateTripRequest;
import org.caravan.model.Trip;
import org.caravan.model.User;
import org.caravan.model.enums.TripStatus;
import org.caravan.repository.TripsRepository;
import org.caravan.security.UserContext;
import org.caravan.services.TripsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;

@QuarkusComponentTest
public class TripsServiceImplTest {

  @Inject
  private TripsServiceImpl tripsService;

  @InjectMock
  private TripsRepository tripsRepository;

  @InjectMock
  private UserContext userContext;

  @BeforeEach
  void setUp() {
    User user = User.builder().id("test|123").email("test@test.com").build();
    Mockito.when(userContext.getCurrentUser()).thenReturn(user);
  }

  @Test
  public void testGetTrips() {
    Trip trip = Trip.builder().name("Test Trip").description("Test Description").date(LocalDate.now())
        .status(TripStatus.UPCOMING).createdBy(userContext.getCurrentUser())
        .participants(List.of(userContext.getCurrentUser()))
        .build();

    Mockito.when(tripsRepository.findTrips(Mockito.any(User.class), Mockito.any())).thenReturn(List.of(trip));
    List<TripResponse> trips = tripsService.getTrips(null);

    assertEquals(1, trips.size());
    assertEquals("Test Trip", trips.get(0).getName());
  }

  @Test
  public void testCreateTrip() {
    CreateTripRequest request = CreateTripRequest.builder().name("Test Trip").description("Test Description")
        .date(LocalDate.now()).build();

    TripResponse tripResponse = tripsService.createTrip(request);
    assertEquals("Test Trip", tripResponse.getName());
  }

  @Test
  public void testGetTripById() {
    Trip trip = Trip.builder().name("Test Trip").description("Test Description").date(LocalDate.now())
        .status(TripStatus.UPCOMING).createdBy(userContext.getCurrentUser())
        .participants(List.of(userContext.getCurrentUser()))
        .build();

    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(trip);
  }

  @Test
  public void testGetTripByIdNotFound() {
    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(null);
    try {
      tripsService.getTripById(1L);
    } catch (Exception e) {
      assertEquals("Trip not found", e.getMessage());
    }
  }

  @Test
  public void testUpdateTrip() {
    Trip trip = Trip.builder().name("Test Trip").description("Test Description").date(LocalDate.now())
        .status(TripStatus.UPCOMING).createdBy(userContext.getCurrentUser())
        .participants(List.of(userContext.getCurrentUser()))
        .build();

    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(trip);

    UpdateTripRequest request = UpdateTripRequest.builder().name("Updated Test Trip")
        .description("Updated Test Description")
        .date(LocalDate.now()).build();

    TripResponse tripResponse = tripsService.updateTrip(1L, request);
    assertEquals("Updated Test Trip", tripResponse.getName());
    assertEquals("Updated Test Description", tripResponse.getDescription());
  }

  @Test
  public void testUpdateTripNotFound() {
    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(null);
    try {
      UpdateTripRequest request = UpdateTripRequest.builder().name("Updated Test Trip")
          .description("Updated Test Description")
          .date(LocalDate.now()).build();
      tripsService.updateTrip(1L, request);
    } catch (Exception e) {
      assertEquals("Trip not found", e.getMessage());
    }
  }

  @Test
  public void testDeleteTrip() {
    Trip trip = Trip.builder().name("Test Trip").description("Test Description").date(LocalDate.now())
        .status(TripStatus.UPCOMING).createdBy(userContext.getCurrentUser())
        .participants(List.of(userContext.getCurrentUser()))
        .build();

    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(trip);

    tripsService.deleteTrip(1L);
    Mockito.verify(tripsRepository).delete(trip);
  }

  @Test
  public void testDeleteTripNotFound() {
    Mockito.when(tripsRepository.findById(Mockito.anyLong())).thenReturn(null);
    try {
      tripsService.deleteTrip(1L);
    } catch (Exception e) {
      assertEquals("Trip not found", e.getMessage());
    }
  }
}