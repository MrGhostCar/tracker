package com.home.tracker.geometry;

import com.home.tracker.vehicle.dto.CoordinatesDTO;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

/** Service for generating Geometry objects, like Points, and for other utility methods. */
@Service
@AllArgsConstructor
public class GeometryService {

  GeometryFactory geometryFactory;

  public Point getCoords(CoordinatesDTO position) {
    return getCoords(position.getLongitude(), position.getLatitude());
  }

  /**
   * Object converter from separate latitude and longitude values to a single Point object, ready to
   * be stored in DB.
   *
   * @param longitude
   * @param latitude
   * @return the new geometric object Point
   */
  public Point getCoords(Double longitude, Double latitude) {
    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
    point.setSRID(4326);
    return point;
  }
}
