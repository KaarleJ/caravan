package org.caravan.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caravan.dto.TripsQueryParams;
import org.caravan.model.Trip;
import org.caravan.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TripsRepository implements PanacheRepository<Trip> {

  public List<Trip> findTrips(User user, TripsQueryParams queryParams) {
    Map<String, Object> params = new HashMap<>();
    params.put("user", user);

    StringBuilder sb = new StringBuilder(
        "(createdBy = :user OR :user MEMBER OF participants)");

    if (queryParams.getStartDate() != null) {
      params.put("start", queryParams.getStartDate());
      sb.append(" AND startDate >= :start");
    }

    if (queryParams.getEndDate() != null) {
      params.put("end", queryParams.getEndDate());
      sb.append(" AND endDate <= :end");
    }

    return find(sb.toString(), params).list();
  }
}
