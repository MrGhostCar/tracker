package com.home.tracker.notification.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationMessageDTO {
    private UUID id;
    private String message;
}
