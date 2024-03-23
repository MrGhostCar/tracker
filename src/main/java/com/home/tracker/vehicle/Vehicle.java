package com.home.tracker.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;
  Double latitude;
  Double longitude;

}
