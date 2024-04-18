package com.home.tracker.vehicle.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.home.tracker.config.Config;
import com.home.tracker.config.LModelMapper;
import com.home.tracker.model.Vehicle;
import com.home.tracker.repository.VehicleRepository;
import com.home.tracker.service.VehicleService;
import com.home.tracker.util.GeometryUtils;
import com.home.tracker.dto.VehiclesInCircleDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceUnitTest {

  Config appConfig = new Config();
  @InjectMocks
  VehicleService vehicleService;
  @Mock
  VehicleRepository vehicleRepository;
  @Spy LModelMapper mapper = appConfig.modelMapper();

  @Test
  void whenVehicleServiceFindCalled_thenRepositoryAlsoCalledWithCorrectParameters() {
    List<Vehicle> testVehicles = new ArrayList<>();

    Double longitude = 47.47581;
    Double latitude = 19.05749;
    Point testCoords = GeometryUtils.getCoords(longitude, latitude);

    Vehicle testVehicle1 = new Vehicle(UUID.randomUUID(), testCoords);
    Vehicle testVehicle2 = new Vehicle(UUID.randomUUID(), testCoords);
    testVehicles.add(testVehicle1);
    testVehicles.add(testVehicle2);

    when(vehicleRepository.findVehiclesInCircle(testCoords, 200L)).thenReturn(testVehicles);

    VehiclesInCircleDTO resultList = vehicleService.findVehiclesInCircle(longitude, latitude, 200L);

    assertEquals(2, resultList.getVehicles().size());
    verify(vehicleRepository, times(1)).findVehiclesInCircle(testCoords, 200L);
  }

  @Test
  void whenRepositoryReturnsLatitude_thenServiceAlsoReturnsLatitude() {
    List<Vehicle> testVehicles = new ArrayList<>();

    Double testLongitude = 47.47581;
    Double testLatitude = 19.05749;
    Point testCoords = GeometryUtils.getCoords(testLongitude, testLatitude);

    Vehicle testVehicle1 = new Vehicle(UUID.randomUUID(), testCoords);
    testVehicles.add(testVehicle1);

    when(vehicleRepository.findVehiclesInCircle(testCoords, 200L)).thenReturn(testVehicles);

    VehiclesInCircleDTO resultList =
        vehicleService.findVehiclesInCircle(testLongitude, testLatitude, 200L);

    assertEquals(testLatitude, resultList.getVehicles().get(0).getLatitude());
  }

  @Test
  void whenRepositoryReturnsLongitude_thenServiceAlsoReturnsLongitude() {
    List<Vehicle> testVehicles = new ArrayList<>();

    Double testLongitude = 47.47581;
    Double testLatitude = 19.05749;
    Point testCoords = GeometryUtils.getCoords(testLongitude, testLatitude);

    Vehicle testVehicle1 = new Vehicle(UUID.randomUUID(), testCoords);
    testVehicles.add(testVehicle1);

    when(vehicleRepository.findVehiclesInCircle(testCoords, 200L)).thenReturn(testVehicles);

    VehiclesInCircleDTO resultList =
        vehicleService.findVehiclesInCircle(testLongitude, testLatitude, 200L);

    assertEquals(testLongitude, resultList.getVehicles().get(0).getLongitude());
  }
}
