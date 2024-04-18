package com.home.tracker.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Response for listing the vehicles in the queried circle on the map. */
@Data
@AllArgsConstructor
public class VehiclesInCircleDTO {
  private List<VehicleResponseDTO> vehicles;
}
