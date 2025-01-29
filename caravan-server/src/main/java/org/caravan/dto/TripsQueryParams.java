package org.caravan.dto;

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
  List<String> status;

  @QueryParam("startDate")
  String startDate;

  @QueryParam("endDate")
  String endDate;
}
