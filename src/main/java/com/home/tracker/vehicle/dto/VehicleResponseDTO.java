package com.home.tracker.vehicle.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VehicleResponseDTO {

    private UUID id;
    private Double latitude;
    private Double longitude;

}
