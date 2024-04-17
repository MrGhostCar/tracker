package com.home.tracker.controller;

import com.home.tracker.service.VehicleService;
import com.home.tracker.dto.CoordinatesDTO;
import com.home.tracker.dto.VehiclesInCircleDTO;
import com.home.tracker.dto.VehicleResponseDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** REST Endpoint definitions for the Vehicle resource. */
@RestController
@AllArgsConstructor
public class VehicleController {

  VehicleService vehicleService;

  @PostMapping("/vehicles")
  public VehicleResponseDTO createVehicle() {
    return vehicleService.createVehicle();
  }

  @PostMapping("/vehicle/{id}")
  public void updateVehicle(@PathVariable UUID id, @RequestBody CoordinatesDTO newPosition) {
    vehicleService.updateVehicle(id, newPosition);
  }

  @GetMapping("/vehicles")
  public VehiclesInCircleDTO getVehiclesInCircle(
      @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Long radius) {
    return vehicleService.findVehiclesInCircle(longitude, latitude, radius);
  }

}