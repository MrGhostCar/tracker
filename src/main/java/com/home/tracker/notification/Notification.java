package com.home.tracker.notification;

import com.home.tracker.vehicle.Vehicle;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  private String message;

  @CreationTimestamp
  private Instant createdOn;

  public boolean vehicleMessageEquals(Notification that) {
    if (this == that) return true;
    return Objects.equals(vehicle.getId(), that.vehicle.getId()) && Objects.equals(message, that.message);
  }

}
