package com.home.tracker.notification.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequestDTO {
    private UUID vehicle_id;
    private String message;
}
