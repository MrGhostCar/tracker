package com.home.tracker.controller;

import com.home.tracker.service.NotificationService;
import com.home.tracker.dto.NotificationRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** REST Endpoint definitions for the notification resource. */
@RestController
@AllArgsConstructor
public class NotificationController {

  NotificationService notificationService;

  @PostMapping("/notifications")
  public void createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
    notificationService.saveNotification(notificationRequestDTO);
  }
}