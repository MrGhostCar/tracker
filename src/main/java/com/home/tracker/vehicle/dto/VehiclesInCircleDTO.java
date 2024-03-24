package com.home.tracker.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VehiclesInCircleDTO {
    private List<VehicleResponseDTO> vehicles;
}
