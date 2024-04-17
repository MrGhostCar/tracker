package com.home.tracker.dto;

import lombok.Data;

import java.util.UUID;

/** Notification Data Transfer Object, for incoming notification requests through REST. */
@Data
public class NotificationRequestDTO {
  private UUID vehicle_id;
  private String message;
}
