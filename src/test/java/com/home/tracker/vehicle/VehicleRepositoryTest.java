package com.home.tracker.vehicle;

import com.home.tracker.model.Vehicle;
import com.home.tracker.repository.VehicleRepository;
import com.home.tracker.util.GeometryUtils;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VehicleRepositoryTest {

    @Autowired
    VehicleRepository vehicleRepository;

    @Test
    public void testCRUD() {
        Point testCoords = GeometryUtils.getCoords(47.47581, 19.05749);
        Vehicle testVehicle = new Vehicle(UUID.randomUUID(), testCoords);
        vehicleRepository.save(testVehicle);

        List<Vehicle> vehicles = vehicleRepository.findAll();

        Assertions.assertThat(vehicles).extracting(Vehicle::getCoordinate).containsOnly(testCoords);

        vehicleRepository.deleteAll();
        Assertions.assertThat(vehicleRepository.findAll()).isEmpty();
    }

}
