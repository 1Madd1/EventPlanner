package org.example.eventplanner.core.repository;

import org.example.eventplanner.core.domain.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    List<Event> findAllEvents();

    Optional<Event> findEventById(UUID id);

    List<Event> findAllEventsByLocalDateTimeBetween(LocalDateTime start, LocalDateTime end);

    Event saveEvent(Event event);

    void deleteEvent(UUID id);
}
