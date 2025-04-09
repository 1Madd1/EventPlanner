package org.example.eventplanner.core.repository;

import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.domain.entity.Guest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventGuestRepository {
    List<EventGuest> findAllEventGuests();

    List<EventGuest> findAllConfirmedEventGuestsByEventId(UUID eventId);

    List<EventGuest> findAllEventGuestsByEventId(UUID eventId);

    List<Guest> findTopFiveConfirmedAndAttendedEventGuests();

    List<Guest> findFrequentNoShowers();

    List<Event> findLowAttendanceEvents();

    Optional<EventGuest> findEventGuestById(UUID id);

    Optional<EventGuest> findEventGuestByEventIdAndGuestId(UUID eventId, UUID guestId);

    EventGuest saveEventGuest(EventGuest event);

    void deleteEventGuest(UUID id);

    List<Guest> findAttendedGuestsByTheme(String theme);
}
