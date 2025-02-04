package org.caravan.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripsQueryParams {

  @QueryParam("status")
  private List<String> status;

  @QueryParam("startDate")
  private LocalDate startDate;

  @QueryParam("endDate")
  private LocalDate endDate;
}
