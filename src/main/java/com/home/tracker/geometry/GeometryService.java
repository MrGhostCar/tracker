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
    return getCoords(position.getLongitude(), position.getLatitude());
  }

  public Point getCoords(Double longitude, Double latitude) {
    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
    point.setSRID(4326);
    return point;
  }

}
