package com.home.tracker.config;

import com.home.tracker.notification.dto.NotificationMessageDTO;
import com.home.tracker.notification.dto.NotificationRequestDTO;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** General Spring configuration, to set up global service beans. */
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

    return modelMapper;
  }

  @Bean
  public GeometryFactory getGeometryFactory() {
    return new GeometryFactory();
  }
}
