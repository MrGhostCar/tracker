package com.home.tracker.vehicle;

import com.home.tracker.vehicle.dto.CoordinatesDTO;
import com.home.tracker.vehicle.dto.VehiclesInCircleDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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



