package com.home.tracker.vehicle;

import com.home.tracker.config.LModelMapper;
import com.home.tracker.geometry.GeometryUtils;
import com.home.tracker.vehicle.dto.CoordinatesDTO;
import com.home.tracker.vehicle.dto.VehiclesInCircleDTO;
import com.home.tracker.vehicle.dto.VehicleResponseDTO;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service defining the business logic for Vehicles, and the process how to store and read
 * Vehicle data from DB, and send websocket message as a new vehicle position.
 */
@Service
@AllArgsConstructor
public class VehicleService {

  public static final String TOPIC_VEHICLE = "/topic/vehicle";

  LModelMapper modelMapper;
  VehicleRepository vehicleRepository;
  SimpMessagingTemplate template;

  public VehicleResponseDTO createVehicle() {
    Vehicle newVehicle = new Vehicle();
    Vehicle savedVehicle = vehicleRepository.save(newVehicle);
    return modelMapper.map(savedVehicle, VehicleResponseDTO.class);
  }

  public void updateVehicle(UUID id, CoordinatesDTO newPosition) {
    Point newCoordinates = GeometryUtils.getCoords(newPosition);
    Vehicle vehicle = new Vehicle(id, newCoordinates);
    vehicleRepository.save(vehicle);

    VehicleResponseDTO vehicleDTO = getVehicleResponse(id, newPosition);
    sendWebsocketMessage(vehicleDTO);
  }

  private void sendWebsocketMessage(VehicleResponseDTO vehicleDTO) {
    template.convertAndSend(TOPIC_VEHICLE, vehicleDTO);
  }

  public VehiclesInCircleDTO findVehiclesInCircle(Double longitude, Double latitude, Long radius) {
    Point center = GeometryUtils.getCoords(longitude, latitude);
    List<Vehicle> foundVehicles = vehicleRepository.findVehiclesInCircle(center, radius);
    List<VehicleResponseDTO> foundVehicleDTOs =
        modelMapper.mapList(foundVehicles, VehicleResponseDTO.class);
    return new VehiclesInCircleDTO(foundVehicleDTOs);
  }

  private VehicleResponseDTO getVehicleResponse(UUID id, CoordinatesDTO newPosition) {
    return new VehicleResponseDTO(id, newPosition.getLongitude(), newPosition.getLatitude());
  }
}
