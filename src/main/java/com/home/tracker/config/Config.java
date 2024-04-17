package com.home.tracker.config;

import com.home.tracker.dto.NotificationMessageDTO;
import com.home.tracker.dto.NotificationRequestDTO;
import com.home.tracker.model.Vehicle;
import com.home.tracker.dto.VehicleResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** General Spring configuration, to set up global beans. */
@Configuration
public class Config {
  @Bean
  public LModelMapper modelMapper() {
    LModelMapper modelMapper = new LModelMapper();

    modelMapper
        .typeMap(NotificationRequestDTO.class, NotificationMessageDTO.class)
        .addMappings(
            mapper -> {
              mapper.map(NotificationRequestDTO::getVehicle_id, NotificationMessageDTO::setId);
            });

    modelMapper
        .typeMap(Vehicle.class, VehicleResponseDTO.class)
        .addMappings(
            mapper -> {
              mapper.map(
                  src -> src.getCoordinate().getCoordinate().getY(),
                  VehicleResponseDTO::setLatitude);
              mapper.map(
                  src -> src.getCoordinate().getCoordinate().getX(),
                  VehicleResponseDTO::setLongitude);
            });

    return modelMapper;
  }

}
