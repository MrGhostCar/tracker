package com.home.tracker.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public LModelMapper modelMapper() {
        return new LModelMapper();
    }

    @Bean
    public GeometryFactory getGeometryFactory() {
        return new GeometryFactory();
    }
}
