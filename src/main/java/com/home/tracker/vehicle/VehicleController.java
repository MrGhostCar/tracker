package com.home.tracker.vehicle;

import com.home.tracker.vehicle.dto.MovementUpdateDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VehicleController {

    VehicleService vehicleService;

    @PostMapping("/vehicles")
    public VehicleResponseDTO createVehicle() {
        return vehicleService.createVehicle();
    }

    @PostMapping("/vehicle/{id}")
    public void updateVehicle(@PathVariable UUID id, @RequestBody MovementUpdateDTO newPosition) {
        vehicleService.updateVehicle(id, newPosition);
    }

}
