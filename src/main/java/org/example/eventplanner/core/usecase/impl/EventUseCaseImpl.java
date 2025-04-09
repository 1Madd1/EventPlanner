package org.example.eventplanner.core.usecase.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.domain.entity.enums.Status;
import org.example.eventplanner.core.error.exception.EventAndGuestMustExistException;
import org.example.eventplanner.core.error.exception.EventGuestNotFoundException;
import org.example.eventplanner.core.error.exception.EventNotFoundException;
import org.example.eventplanner.core.error.exception.EventScheduledDateAlreadyExistsException;
import org.example.eventplanner.core.repository.EventGuestRepository;
import org.example.eventplanner.core.repository.EventRepository;
import org.example.eventplanner.core.repository.GuestRepository;
import org.example.eventplanner.core.usecase.EventUseCase;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Builder
public class EventUseCaseImpl implements EventUseCase {

    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;
    private final EventGuestRepository eventGuestRepository;

    @Override
    public Event createEvent(Event event) {
        for (Event e : eventRepository.findAllEvents()) {
            if (event.getDateOfEvent().isEqual(e.getDateOfEvent())) {
                throw new EventScheduledDateAlreadyExistsException("Event with scheduled date" + event.getDateOfEvent() + " already exists!");
            }
        }
        return eventRepository.saveEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        List<EventGuest> listOfConfirmedEventGuests;
        List<Guest> listOfConfirmedGuests;
        Event updatedEvent = eventRepository.saveEvent(event);
        listOfConfirmedEventGuests = eventGuestRepository.findAllConfirmedEventGuestsByEventId(updatedEvent.getId());
        listOfConfirmedGuests = new ArrayList<>();
        for (EventGuest eg : listOfConfirmedEventGuests) {
            listOfConfirmedGuests.add(eg.getGuest());
        }
        if (updatedEvent.getStatus().equals(Status.CANCELED)) {
            // Simulating as if we are sending an email notification of event cancellation
            for (Guest guest : listOfConfirmedGuests) {
                System.out.println("Sending email to " + guest.getEmail() + " about cancellation of event " + updatedEvent.getTitle());
            }
        } else {
            // Simulating as if we are sending an email notification of updated event
            for (Guest guest : listOfConfirmedGuests) {
                System.out.println("Sending email to " + guest.getEmail() + " for notification of updated event " + updatedEvent.getTitle());
            }
        }
        return eventRepository.saveEvent(event);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public List<Guest> findAllInvitedGuestByEventId(UUID eventId) {
        List<Guest> invitedGuests = new ArrayList<>();
        for (EventGuest eg : eventGuestRepository.findAllEventGuests()) {
            if (eg.getEvent().getId().equals(eventId)) {
                invitedGuests.add(eg.getGuest());
            }
        }
        return invitedGuests;
    }

    @Override
    public Event findEventById(UUID eventId) {
        Optional<Event> event = eventRepository.findEventById(eventId);
        if (event.isEmpty()) {
            throw new EventNotFoundException("Event with id " + eventId + " not found!");
        }
        return event.get();
    }

    @Override
    public void deleteEvent(UUID id) {
        eventRepository.deleteEvent(id);
    }

    @Override
    public EventGuest inviteGuest(UUID eventId, UUID guestId) {
        Optional<Event> event = eventRepository.findEventById(eventId);
        Optional<Guest> invitedGuest = guestRepository.findGuestById(guestId);

        if (event.isPresent() && invitedGuest.isPresent()) {
                return eventGuestRepository.saveEventGuest(EventGuest.builder()
                        .event(event.get())
                        .guest(invitedGuest.get())
                        .attended(false)
                        .attending(false)
                        .build());
        }

        throw new EventAndGuestMustExistException("Event with id " + eventId + " and/or guest with id " + guestId + " not found!");
    }

    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void notifyConfirmedInvitedGuestsForUpcomingEvent() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime windowStart = now;
        LocalDateTime windowEnd = now.plusDays(1);
        List<Event> upcomingEventsList = eventRepository.findAllEventsByLocalDateTimeBetween(windowStart, windowEnd);
        if (!upcomingEventsList.isEmpty()) {
            List<EventGuest> eventGuestList;
            Map<Guest, Event> confirmedGuestsWithEventMap = new HashMap<>();
            for (Event e : upcomingEventsList) {
                eventGuestList = eventGuestRepository.findAllEventGuestsByEventId(e.getId());
                for (EventGuest eg : eventGuestList) {
                    if (eg.isAttending()) {
                        confirmedGuestsWithEventMap.put(eg.getGuest(), eg.getEvent());
                    }
                }
            }
            // Simulating as if we are sending an email notification of an upcoming event cancellation
            for (Map.Entry<Guest, Event> entry : confirmedGuestsWithEventMap.entrySet()) {
                Guest guest = entry.getKey();
                Event event = entry.getValue();
                System.out.println("Sending email to " + guest.getEmail() + " for notification of upcoming event " + event.getTitle());
            }
        } else {
            System.out.println("No upcoming events found!");
            System.out.println(windowStart);
        }
    }

    @Override
    public EventGuest acceptInvite(UUID eventId, UUID guestId) {
        Optional<EventGuest> optionalEventGuest = eventGuestRepository.findEventGuestByEventIdAndGuestId(eventId, guestId);
        if (optionalEventGuest.isPresent()) {
            EventGuest eventGuest = optionalEventGuest.get();
            eventGuest.setAttending(true);
            return eventGuestRepository.saveEventGuest(eventGuest);
        } else {
            throw new EventGuestNotFoundException("EventGuest with eventId " + eventId + " and guestId " + guestId + " not found!");
        }
    }

}