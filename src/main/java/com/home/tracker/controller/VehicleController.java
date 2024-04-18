package com.home.tracker.controller;

import com.home.tracker.dto.CoordinatesDTO;
import com.home.tracker.dto.VehicleResponseDTO;
import com.home.tracker.dto.VehiclesInCircleDTO;
import com.home.tracker.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

/** REST Endpoint definitions for the Vehicle resource. */
@RestController
@AllArgsConstructor
public class VehicleController {

  VehicleService vehicleService;

  @Operation(summary = "A vehicle registers itself to the server.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "The list of vehicles in the circle")
      })
  @PostMapping("/vehicles")
  public VehicleResponseDTO createVehicle() {
    return vehicleService.createVehicle();
  }

  @Operation(summary = "A vehicle sends its new coordinates to the server with 10 Hz.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "The position update was succesful."),
        @ApiResponse(responseCode = "400", description = "Invalid parameters.")
      })
  @PostMapping("/vehicle/{id}")
  public void updateVehicle(@PathVariable UUID id, @Valid @RequestBody CoordinatesDTO newPosition) {
    vehicleService.updateVehicle(id, newPosition);
  }

  @Operation(summary = "Query the vehicles in the circle with the given center point and radius.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "The list of vehicles in the circle",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = VehiclesInCircleDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid parameters.", content = @Content)
      })
  @GetMapping("/vehicles")
  public VehiclesInCircleDTO getVehiclesInCircle(
      @RequestParam @NotNull(message = "Latitude is required.") @Range(min = -90, max = 90)
          Double latitude,
      @RequestParam @NotNull(message = "Longitude is required.") @Range(min = -180, max = 180)
          Double longitude,
      @RequestParam @NotNull(message = "Radius is required.") @Min(1) Long radius) {
    return vehicleService.findVehiclesInCircle(longitude, latitude, radius);
  }
}
