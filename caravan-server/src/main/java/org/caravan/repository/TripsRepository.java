package org.caravan.repository;

import org.caravan.model.Trip;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TripsRepository implements PanacheRepository<Trip> {
  
}
