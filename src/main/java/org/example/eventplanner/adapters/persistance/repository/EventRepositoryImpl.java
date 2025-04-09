package org.example.eventplanner.adapters.persistance.repository;

import lombok.Builder;
import org.example.eventplanner.adapters.persistance.dao.EventDao;
import org.example.eventplanner.adapters.persistance.mapper.EventMapperDB;
import org.example.eventplanner.adapters.persistance.repository.jpa.EventJpaRepository;
import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.error.exception.InvalidEventScheduledTimeException;
import org.example.eventplanner.core.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Builder
public class EventRepositoryImpl implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    @Override
    public List<Event> findAllEvents() {
        return EventMapperDB.INSTANCE.eventDaoListToEventList(eventJpaRepository.findAll());
    }

    @Override
    public Optional<Event> findEventById(UUID id) {
        Event event = EventMapperDB.INSTANCE.eventDaoToEvent(eventJpaRepository.findById(id).orElse(null));
        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAllEventsByLocalDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return EventMapperDB.INSTANCE.eventDaoListToEventList(eventJpaRepository.findAllByDateOfEventBetween(start, end));
    }

    @Override
    public Event saveEvent(Event event) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (event.getDateOfEvent().isBefore(currentTime.plusDays(1))) {
            throw new InvalidEventScheduledTimeException("You have to schedule an event a day after the current time");
        }
        EventDao eventDao = EventMapperDB.INSTANCE.eventToEventDao(event);
        return EventMapperDB.INSTANCE.eventDaoToEvent(eventJpaRepository.save(eventDao));
    }

    @Override
    public void deleteEvent(UUID id) {
        eventJpaRepository.deleteById(id);
    }
}
