package com.home.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private UUID id;
    private Double longitude;
    private Double latitude;
}
