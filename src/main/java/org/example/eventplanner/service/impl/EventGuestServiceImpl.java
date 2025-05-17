package org.example.eventplanner.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.dto.EventGuestDto;
import org.example.eventplanner.entity.*;
import org.example.eventplanner.entity.enums.Theme;
import org.example.eventplanner.error.exception.EntityAlreadyExistsException;
import org.example.eventplanner.error.exception.EntityNotFoundException;
import org.example.eventplanner.repository.EventGuestRepository;
import org.example.eventplanner.repository.EventRepository;
import org.example.eventplanner.repository.GuestRepository;
import org.example.eventplanner.service.EventGuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Builder
@Service
@RequiredArgsConstructor
public class EventGuestServiceImpl implements EventGuestService {

    private final EventGuestRepository eventGuestRepository;
    private final GuestRepository guestRepository;
    private final EventRepository eventRepository;

    @Override
    public EventGuest createEventGuest(EventGuestDto eventGuestDto) {
        if (eventGuestRepository.findByEventIdAndGuestId(eventGuestDto.getEventId(), eventGuestDto.getGuestId()).isPresent()) {
            throw new EntityAlreadyExistsException("EventGuest with eventId: " + eventGuestDto.getEventId() + " and guestId: " + eventGuestDto.getGuestId() + " already exists!");
        }

        return eventGuestRepository.save(EventGuest.builder()
                .event(Event.builder()
                        .id(eventGuestDto.getEventId())
                        .build())
                .guest(Guest.builder()
                        .id(eventGuestDto.getGuestId())
                        .build())
                .build());
    }

    @Override
    public EventGuest updateEventGuest(EventGuestDto eventGuestDto) {
        return eventGuestRepository.save(EventGuest.builder()
                .event(Event.builder()
                        .id(eventGuestDto.getEventId())
                        .build())
                .guest(Guest.builder()
                        .id(eventGuestDto.getGuestId())
                        .build())
                .build());
    }

    @Override
    public Page<EventGuest> findAllEventGuests(Pageable pageable) {
        return eventGuestRepository.findAll(pageable);
    }

    @Override
    public Page<EventGuest> findAllEventGuestsByEventId(UUID eventId, Pageable pageable) {
        return eventGuestRepository.findAllByEventId(eventId, pageable);
    }

    @Override
    public List<Guest> findTopFiveConfirmedAndAttendedGuests() {
        List<Guest> topFiveConfirmedAndAttendedEventGuests = new ArrayList<>();
        List<Top5Guests> tgsdList = eventGuestRepository.findTop5ConfirmedAttendedGuests();
        tgsdList.sort(Comparator.comparingLong(Top5Guests::getAttendanceCount));
        for (Top5Guests tgsd : tgsdList) {
            Optional<Guest> optionalGuest = guestRepository.findById(tgsd.getGuestId());
            optionalGuest.ifPresent(topFiveConfirmedAndAttendedEventGuests::add);
        }
        return topFiveConfirmedAndAttendedEventGuests;
    }

    @Override
    public List<Guest> findFrequentNoShowers(Pageable pageable) {
        List<Guest> frequentNoShowingGuestList = new ArrayList<>();
        List<FrequentNoShowers> frequentNoShowersList = eventGuestRepository.findFrequentNoShowers();
        for (FrequentNoShowers fn : frequentNoShowersList) {
            Optional<Guest> optionalGuest = guestRepository.findById(fn.getGuestId());
            optionalGuest.ifPresent(frequentNoShowingGuestList::add);
        }
        return frequentNoShowingGuestList;
    }

    @Override
    public Page<Event> findLowAttendanceEvents(Pageable pageable) {
        List<UUID> lowAttendanceEventsIdList = eventGuestRepository.findLowAttendanceEvents();
        List<Event> lowAttendanceEventsList = new ArrayList<>();
        for (UUID id : lowAttendanceEventsIdList) {
            Optional<Event> optionalEvent = eventRepository.findById(id);
            optionalEvent.ifPresent(lowAttendanceEventsList::add);
        }
        return new PageImpl<>(lowAttendanceEventsList, pageable, lowAttendanceEventsList.size());
    }

    @Override
    public EventGuest findEventGuestById(UUID eventGuestId) {
        Optional<EventGuest> eventGuest = eventGuestRepository.findById(eventGuestId);
        if (eventGuest.isEmpty()) {
            throw new EntityNotFoundException("EventGuest with id " + eventGuestId + " not found!");
        }
        return eventGuest.get();
    }

    @Override
    public EventGuest findEventGuestByEventIdAndGuestId(UUID eventId, UUID guestId) {
        Optional<EventGuest> eventGuest = eventGuestRepository.findByEventIdAndGuestId(eventId, guestId);
        if (eventGuest.isEmpty()) {
            throw new EntityNotFoundException("EventGuest with eventId: " + eventId + " and guestId: " + guestId + " not found!");
        }
        return eventGuest.get();
    }

    @Override
    public Page<Guest> findAttendedGuestsByTheme(Theme theme, Pageable pageable) {
        List<UUID> guestIdList = eventGuestRepository.findGuestsAttendedAllEventsWithGivenTheme(theme);
        List<Guest> attendedGuestsByThemeList = new ArrayList<>();
        for (UUID id : guestIdList) {
            Optional<Guest> optionalGuest = guestRepository.findById(id);
            optionalGuest.ifPresent(attendedGuestsByThemeList::add);
        }
        return new PageImpl<>(attendedGuestsByThemeList, pageable, attendedGuestsByThemeList.size());
    }

    @Override
    public void deleteEventGuest(UUID id) {
        eventGuestRepository.deleteById(id);
    }
}
