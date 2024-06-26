package com.home.tracker.service;

import com.home.tracker.config.LModelMapper;
import com.home.tracker.model.Notification;
import com.home.tracker.dto.NotificationMessageDTO;
import com.home.tracker.dto.NotificationRequestDTO;
import com.home.tracker.repository.NotificationRepository;
import com.home.tracker.model.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service defining the business logic for Notifications, and the process how to store and read
 * Notifications from DB, and send websocket message as a notification.
 */
@Service
@AllArgsConstructor
public class NotificationService {
  public static final String TOPIC_NOTIFICATION = "/topic/notification";

  LModelMapper modelMapper;
  NotificationRepository notificationRepository;
  @PersistenceContext private EntityManager entityManager;
  SimpMessagingTemplate template;

  public void saveAndSendNotification(NotificationRequestDTO notificationRequestDTO) {
    Notification newNotification = modelMapper.map(notificationRequestDTO, Notification.class);
    Vehicle targetVehicle =
        entityManager.getReference(Vehicle.class, notificationRequestDTO.getVehicle_id());
    newNotification.setVehicle(targetVehicle);

    boolean isTheSameAsLast = isTheSameNotificationAsLast(newNotification);
    notificationRepository.save(newNotification);

    if (!isTheSameAsLast) {
      sendWebSocketMessage(notificationRequestDTO);
    }
  }

  private void sendWebSocketMessage(NotificationRequestDTO notificationRequestDTO) {
    NotificationMessageDTO message =
        modelMapper.map(notificationRequestDTO, NotificationMessageDTO.class);
    template.convertAndSend(TOPIC_NOTIFICATION, message);
  }

  private boolean isTheSameNotificationAsLast(Notification newNotification) {
    Optional<Notification> lastMessageOfVehicle =
        notificationRepository.findFirstByVehicleIdOrderByCreatedOnDesc(
            newNotification.getVehicle().getId());

    return lastMessageOfVehicle.filter(newNotification::vehicleMessageEquals).isPresent();
  }
}
