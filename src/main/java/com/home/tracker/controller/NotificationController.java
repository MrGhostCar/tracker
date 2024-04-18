package com.home.tracker.controller;

import com.home.tracker.dto.NotificationRequestDTO;
import com.home.tracker.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** REST Endpoint definitions for the notification resource. */
@RestController
@AllArgsConstructor
public class NotificationController {

  NotificationService notificationService;

  @Operation(summary = "A vehicle sends a notification to the server and to the front-end.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "The notification broadcast to client was succesful.")
      })
  @PostMapping("/notifications")
  public void createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
    notificationService.saveAndSendNotification(notificationRequestDTO);
  }
}
