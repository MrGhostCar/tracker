package com.home.tracker.vehicle;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VehicleController {

    VehicleService vehicleService;

    @PostMapping("/vehicles")
    public VehicleResponseDTO createVehicle() {
        return vehicleService.createVehicle();
    }

}
