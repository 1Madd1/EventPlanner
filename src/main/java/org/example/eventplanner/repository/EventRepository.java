package org.example.eventplanner.repository;


import org.example.eventplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByDateOfEventBetween(LocalDateTime start, LocalDateTime end);
}
