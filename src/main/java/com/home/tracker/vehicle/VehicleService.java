package com.home.tracker.vehicle;

import com.home.tracker.geometry.GeometryService;
import com.home.tracker.vehicle.dto.CoordinatesDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {

  ModelMapper modelMapper;
  VehicleRepository vehicleRepository;
  GeometryService geometryService;

  public VehicleResponseDTO createVehicle() {
    Vehicle newVehicle = new Vehicle();
    Vehicle savedVehicle = vehicleRepository.save(newVehicle);
    return modelMapper.map(savedVehicle, VehicleResponseDTO.class);
  }

  public void updateVehicle(UUID id, CoordinatesDTO newPosition) {
    Point newCoordinates = geometryService.getCoords(newPosition);
    Vehicle vehicle = new Vehicle(id, newCoordinates);
    vehicleRepository.save(vehicle);
  }
}
