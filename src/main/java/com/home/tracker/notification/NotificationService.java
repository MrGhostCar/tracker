package com.home.tracker.notification;

import com.home.tracker.config.LModelMapper;
import com.home.tracker.notification.dto.NotificationRequestDTO;
import com.home.tracker.vehicle.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
  LModelMapper modelMapper;
  NotificationRepository notificationRepository;
  @PersistenceContext private EntityManager entityManager;

  public void saveNotification(NotificationRequestDTO notificationRequestDTO) {
    Notification notification = modelMapper.map(notificationRequestDTO, Notification.class);
    Vehicle targetVehicle =
        entityManager.getReference(Vehicle.class, notificationRequestDTO.getVehicle_id());
    notification.setVehicle(targetVehicle);
    notificationRepository.save(notification);
  }
}
