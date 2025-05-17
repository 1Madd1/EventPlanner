package org.example.eventplanner.repository;


import org.example.eventplanner.entity.Event;
import org.example.eventplanner.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByDateOfEventBetweenAndStatus(LocalDateTime start, LocalDateTime end, Status status);
    Optional<Event> findByDateOfEvent(LocalDateTime date);
}
