package com.home.tracker.vehicle;

import java.util.List;
import java.util.UUID;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query(value="SELECT * FROM vehicle WHERE ST_Distance(coordinate, :center) < :distanceMeter", nativeQuery = true)
    List<Vehicle> findVehiclesInCircle(Point center, long distanceMeter);

}
