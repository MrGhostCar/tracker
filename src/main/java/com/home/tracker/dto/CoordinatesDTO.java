package com.home.tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {
    @NotNull(message = "Latitude is required.")
    @Range(min = -90, max = 90)
    private Double latitude;

    @NotNull(message = "Longitude is required.")
    @Range(min = -180, max = 180)
    private Double longitude;
}
