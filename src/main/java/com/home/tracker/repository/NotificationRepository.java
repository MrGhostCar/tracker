package com.home.tracker.repository;

import com.home.tracker.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/** A JPA data access object, for storing and retrieving Notification records for the DB. */
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

  Optional<Notification> findFirstByVehicleIdOrderByCreatedOnDesc(UUID id);
}
