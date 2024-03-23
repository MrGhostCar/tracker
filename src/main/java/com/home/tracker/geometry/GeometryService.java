package com.home.tracker.geometry;

import com.home.tracker.vehicle.dto.CoordinatesDTO;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeometryService {

  GeometryFactory geometryFactory;

  public Point getCoords(CoordinatesDTO position) {
    Point point =
        geometryFactory.createPoint(
            new Coordinate(position.getLongitude(), position.getLatitude()));
    point.setSRID(4326);
    return point;
  }

}
