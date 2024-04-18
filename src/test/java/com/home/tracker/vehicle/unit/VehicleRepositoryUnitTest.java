package com.home.tracker.vehicle.unit;

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
public class VehicleRepositoryUnitTest {

    @Autowired
    VehicleRepository vehicleRepository;

    @Test
    public void givenVehiclesWereStored_whenFindAllCalled_thenSameGeometryCoordsAreRecoveredAsSaved() {
        Point testCoords = GeometryUtils.getCoords(47.47581, 19.05749);
        Vehicle testVehicle = new Vehicle(UUID.randomUUID(), testCoords);
        vehicleRepository.save(testVehicle);

        List<Vehicle> vehicles = vehicleRepository.findAll();

        Assertions.assertThat(vehicles).extracting(Vehicle::getCoordinate).contains(testCoords);
    }

    @Test
    public void givenVehiclesWereStored_whenDeleteAllCalled_thenRepoWillBeEmpty() {
        Point testCoords = GeometryUtils.getCoords(47.47581, 19.05749);
        Vehicle testVehicle = new Vehicle(UUID.randomUUID(), testCoords);
        vehicleRepository.save(testVehicle);

        Assertions.assertThat(vehicleRepository.findAll()).isNotEmpty();

        vehicleRepository.deleteAll();
        Assertions.assertThat(vehicleRepository.findAll()).isEmpty();
    }

}
