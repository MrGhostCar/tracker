package com.home.tracker.notification;

import com.home.tracker.config.LModelMapper;
import com.home.tracker.notification.dto.NotificationMessageDTO;
import com.home.tracker.notification.dto.NotificationRequestDTO;
import com.home.tracker.vehicle.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
  LModelMapper modelMapper;
  NotificationRepository notificationRepository;
  @PersistenceContext private EntityManager entityManager;
  SimpMessagingTemplate template;

  public void saveNotification(NotificationRequestDTO notificationRequestDTO) {
    Notification newNotification = modelMapper.map(notificationRequestDTO, Notification.class);
    Vehicle targetVehicle =
        entityManager.getReference(Vehicle.class, notificationRequestDTO.getVehicle_id());
    newNotification.setVehicle(targetVehicle);
    boolean isTheSameAsLast = isTheSameNotificationAsLast(newNotification);
    notificationRepository.save(newNotification);
    if (!isTheSameAsLast) {
      NotificationMessageDTO message =
          modelMapper.map(notificationRequestDTO, NotificationMessageDTO.class);
      template.convertAndSend("/topic/notification", message);
    }
  }

  private boolean isTheSameNotificationAsLast(Notification newNotification) {
    Optional<Notification> lastMessageOfVehicle =
        notificationRepository.findFirstByVehicleIdOrderByCreatedOnDesc(newNotification.getVehicle().getId());
    return lastMessageOfVehicle.filter(newNotification::vehicleMessageEquals).isPresent();
  }
}
