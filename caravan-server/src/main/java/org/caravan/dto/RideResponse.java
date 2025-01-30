package org.caravan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideResponse {
  private Long id;
  private Long tripId;
  private Long driverId;
  private int seats;
}
