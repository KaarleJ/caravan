package org.caravan.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripResponse {
  private Long id;
  private String name;
  private String description;
  private LocalDate date;
  private String createdById;
  private List<UserResponse> participants;
  private List<RideResponse> rides;
}
