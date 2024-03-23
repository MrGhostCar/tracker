package com.home.tracker.vehicle;

import com.home.tracker.vehicle.dto.MovementUpdateDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleService {

    VehicleRepository vehicleRepository;
    ModelMapper modelMapper;

    public VehicleResponseDTO createVehicle() {
        Vehicle newVehicle = new Vehicle();
        Vehicle savedVehicle = vehicleRepository.save(newVehicle);
        return modelMapper.map(savedVehicle, VehicleResponseDTO.class);
    }

    public void updateVehicle(UUID id, MovementUpdateDTO newPosition) {
        Vehicle vehicle = new Vehicle(id, newPosition.getLatitude(), newPosition.getLongitude());
        vehicleRepository.save(vehicle);
    }

}
