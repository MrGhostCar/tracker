package com.home.tracker.notification;

import com.home.tracker.notification.dto.NotificationRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NotificationController {

    NotificationService notificationService;

    @PostMapping("/notifications")
    public void createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        notificationService.saveNotification(notificationRequestDTO);
    }

}
