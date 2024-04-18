package com.home.tracker.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Outgoing DTO for transmitting vehicle data. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
  private UUID id;
  private Double longitude;
  private Double latitude;
}
