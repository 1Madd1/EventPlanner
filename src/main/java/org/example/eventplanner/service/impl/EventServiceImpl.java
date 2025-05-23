package org.example.eventplanner.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.dto.EventDto;
import org.example.eventplanner.entity.Event;
import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.entity.enums.Status;
import org.example.eventplanner.error.exception.EntityNotFoundException;
import org.example.eventplanner.error.exception.EventScheduledDateAlreadyExistsException;
import org.example.eventplanner.error.exception.InvalidEventScheduledTimeException;
import org.example.eventplanner.repository.EventGuestRepository;
import org.example.eventplanner.repository.EventRepository;
import org.example.eventplanner.repository.GuestRepository;
import org.example.eventplanner.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Builder
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;
    private final EventGuestRepository eventGuestRepository;

    @Override
    public Event createEvent(EventDto eventDto) {
        if (eventRepository.findByDateOfEvent(eventDto.getDateOfEvent()).isPresent()) {
            throw new EventScheduledDateAlreadyExistsException("Event with scheduled date" + eventDto.getDateOfEvent() + " already exists!");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        if (eventDto.getDateOfEvent().isBefore(currentTime.plusDays(1))) {
            throw new InvalidEventScheduledTimeException("You have to schedule an event a day after the current time");
        }
        return eventRepository.save(Event.builder()
                .dateOfEvent(eventDto.getDateOfEvent())
                .theme(eventDto.getTheme())
                .status(eventDto.getStatus())
                .title(eventDto.getTitle())
                .location(eventDto.getLocation())
                .build());
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
        List<EventGuest> listOfConfirmedEventGuests;
        List<Guest> listOfConfirmedGuests;
        LocalDateTime currentTime = LocalDateTime.now();
        if (eventDto.getDateOfEvent().isBefore(currentTime.plusDays(1))) {
            throw new InvalidEventScheduledTimeException("You have to schedule an event a day after the current time");
        }
        Event updatedEvent = eventRepository.save(Event.builder()
                .dateOfEvent(eventDto.getDateOfEvent())
                .theme(eventDto.getTheme())
                .status(eventDto.getStatus())
                .title(eventDto.getTitle())
                .location(eventDto.getLocation())
                .build());
        listOfConfirmedEventGuests = eventGuestRepository.findAllByEventIdAndAttendingTrue(updatedEvent.getId());
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
        return updatedEvent;
    }

    @Override
    public Page<Event> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Page<Guest> findAllInvitedGuestByEventId(UUID eventId, Pageable pageable) {
        List<Guest> invitedGuests = new ArrayList<>();
        for (EventGuest eg : eventGuestRepository.findAllByEventId(eventId, pageable)) {
            invitedGuests.add(eg.getGuest());
        }
        return new PageImpl<>(invitedGuests, pageable, invitedGuests.size());
    }

    @Override
    public Event findEventById(UUID eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new EntityNotFoundException("Event with id " + eventId + " not found!");
        }
        return event.get();
    }

    @Override
    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }

    @Override
    public EventGuest inviteGuest(UUID eventId, UUID guestId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<Guest> invitedGuest = guestRepository.findById(guestId);

        if (event.isPresent() && invitedGuest.isPresent()) {
            return eventGuestRepository.save(EventGuest.builder()
                    .event(event.get())
                    .guest(invitedGuest.get())
                    .attended(false)
                    .attending(false)
                    .build());
        }

        throw new EntityNotFoundException("Event with id " + eventId + " and/or guest with id " + guestId + " not found!");
    }

    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void notifyConfirmedInvitedGuestsForUpcomingEvent() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime windowStart = now;
        LocalDateTime windowEnd = now.plusDays(1);
        List<Event> upcomingEventsList = eventRepository.findAllByDateOfEventBetweenAndStatus(windowStart, windowEnd, Status.UPCOMING);
        if (!upcomingEventsList.isEmpty()) {
            List<EventGuest> eventGuestList;
            Map<Guest, Event> confirmedGuestsWithEventMap = new HashMap<>();
            for (Event e : upcomingEventsList) {
                Pageable pageable = PageRequest.of(0, 5);
                eventGuestList = eventGuestRepository.findAllByEventId(e.getId(), pageable).getContent();
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
        Optional<EventGuest> optionalEventGuest = eventGuestRepository.findByEventIdAndGuestId(eventId, guestId);
        if (optionalEventGuest.isPresent()) {
            EventGuest eventGuest = optionalEventGuest.get();
            eventGuest.setAttending(true);
            return eventGuestRepository.save(eventGuest);
        } else {
            throw new EntityNotFoundException("EventGuest with eventId " + eventId + " and guestId " + guestId + " not found!");
        }
    }

}