package com.home.tracker.vehicle;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

}
