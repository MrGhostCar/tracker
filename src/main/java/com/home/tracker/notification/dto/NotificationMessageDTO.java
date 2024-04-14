package com.home.tracker.notification.dto;

import java.util.UUID;
import lombok.Data;

/** Notification message Data Transfer Object, for websocket messages. */
@Data
public class NotificationMessageDTO {
  private UUID id;
  private String message;
}
