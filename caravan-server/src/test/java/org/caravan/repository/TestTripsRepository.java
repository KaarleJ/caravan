package org.caravan.repository;

import java.time.LocalDate;

import org.caravan.model.Trip;
import org.caravan.model.User;
import org.caravan.model.enums.TripStatus;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Priority(1)
@Alternative
@ApplicationScoped
public class TestTripsRepository extends TripsRepository {

  @Inject
  EntityManager em;

  @PostConstruct
  public void init() {
    User userref = em.getReference(User.class, "test|1234");

    Trip trip = Trip.builder().name("Tatooine Shenanigans").description("Visit Tatooine")
        .date(LocalDate.of(2025, 2, 19))
        .status(TripStatus.UPCOMING)
        .createdBy(userref).build();

    persist(trip);
  }
}
