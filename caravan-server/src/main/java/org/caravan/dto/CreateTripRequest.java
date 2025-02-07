package org.caravan.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTripRequest {
  @NotBlank(message = "name is required")
  private String name;

  private String description;

  @NotNull(message = "date is required")
  private LocalDate date;
}
