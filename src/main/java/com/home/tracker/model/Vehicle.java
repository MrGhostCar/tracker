package com.home.tracker.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

/** The Vehicle hibernate entity. */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(columnDefinition = "geography")
  Point coordinate;
}
