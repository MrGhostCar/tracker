package com.home.tracker.vehicle;

import com.home.tracker.config.LModelMapper;
import com.home.tracker.geometry.GeometryService;
import com.home.tracker.vehicle.dto.CoordinatesDTO;
import com.home.tracker.vehicle.dto.VehiclesInCircleDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {

  LModelMapper modelMapper;
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

  public VehiclesInCircleDTO findVehiclesInCircle(Double longitude, Double latitude, Long radius) {
    Point center = geometryService.getCoords(longitude, latitude);
    List<Vehicle> foundVehicles = vehicleRepository.findVehiclesInCircle(center, radius);
    List<VehicleResponseDTO> foundVehicleDTOs =
        modelMapper.mapList(foundVehicles, VehicleResponseDTO.class);
    return new VehiclesInCircleDTO(foundVehicleDTOs);
  }
}
