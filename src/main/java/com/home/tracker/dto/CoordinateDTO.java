package com.home.tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/** Simple DTO contains a global coordinate. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateDTO {
  @NotNull(message = "Latitude is required.")
  @Range(min = -90, max = 90)
  private Double latitude;

  @NotNull(message = "Longitude is required.")
  @Range(min = -180, max = 180)
  private Double longitude;
}
