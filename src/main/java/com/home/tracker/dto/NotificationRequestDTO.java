package com.home.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

/** Notification Data Transfer Object, for incoming notification requests through REST. */
@Data
public class NotificationRequestDTO {
  @NotNull(message = "Vehicle_id must not be null.")
  private UUID vehicle_id;
  @NotBlank(message = "Latitude is required.")
  private String message;
}
