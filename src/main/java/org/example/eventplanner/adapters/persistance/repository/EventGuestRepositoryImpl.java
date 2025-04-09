package org.example.eventplanner.adapters.persistance.repository;

import lombok.Builder;
import org.example.eventplanner.adapters.persistance.dao.*;
import org.example.eventplanner.adapters.persistance.mapper.EventGuestMapperDB;
import org.example.eventplanner.adapters.persistance.mapper.EventMapperDB;
import org.example.eventplanner.adapters.persistance.mapper.GuestMapperDB;
import org.example.eventplanner.adapters.persistance.repository.jpa.EventGuestJpaRepository;
import org.example.eventplanner.adapters.persistance.repository.jpa.EventJpaRepository;
import org.example.eventplanner.adapters.persistance.repository.jpa.GuestJpaRepository;
import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.domain.entity.enums.Themes;
import org.example.eventplanner.core.repository.EventGuestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Builder
public class EventGuestRepositoryImpl implements EventGuestRepository {

    private final EventGuestJpaRepository eventGuestJpaRepository;
    private final GuestJpaRepository guestJpaRepository;
    private final EventJpaRepository eventJpaRepository;

    @Override
    public List<EventGuest> findAllEventGuests() {
        return EventGuestMapperDB.INSTANCE.eventGuestDaoListToEventGuestList(eventGuestJpaRepository.findAll());
    }

    @Override
    public List<EventGuest> findAllConfirmedEventGuestsByEventId(UUID eventId) {
        return EventGuestMapperDB.INSTANCE.eventGuestDaoListToEventGuestList(eventGuestJpaRepository.findAllByEventIdAndAttendingTrue(eventId));
    }

    @Override
    public List<EventGuest> findAllEventGuestsByEventId(UUID eventId) {
        return EventGuestMapperDB.INSTANCE.eventGuestDaoListToEventGuestList(eventGuestJpaRepository.findAllByEventId(eventId));
    }

    @Override
    public List<Guest> findTopFiveConfirmedAndAttendedEventGuests() {
        List<Guest> topFiveConfirmedAndAttendedEventGuests = new ArrayList<>();
        List<Top5GuestsDao> tgsdList = eventGuestJpaRepository.findTop5ConfirmedAttendedGuests();
        tgsdList.sort( (a, b) -> {
            if (a.getAttendanceCount() > b.getAttendanceCount()) {
                return 1;
            } else if (a.getAttendanceCount() < b.getAttendanceCount()) {
                return -1;
            } else {
                return 0;
            }
        });
        for (Top5GuestsDao tgsd : tgsdList) {
            Optional<GuestDao> optionalGuestDao = guestJpaRepository.findById(tgsd.getGuestId());
            if (optionalGuestDao.isPresent()) {
                topFiveConfirmedAndAttendedEventGuests.add(GuestMapperDB.INSTANCE.guestDaoToGuest(optionalGuestDao.get()));
            }
        }
        return topFiveConfirmedAndAttendedEventGuests;
    }

    @Override
    public List<Guest> findFrequentNoShowers() {
        List<Guest> frequentNoShowersList = new ArrayList<>();
        List<FrequentNoShowersDao> frequentNoShowersDaoList = eventGuestJpaRepository.findFrequentNoShowers();
        for (FrequentNoShowersDao fnd : frequentNoShowersDaoList) {
            Optional<GuestDao> optionalGuestDao = guestJpaRepository.findById(fnd.getGuestId());
            if (optionalGuestDao.isPresent()) {
                frequentNoShowersList.add(GuestMapperDB.INSTANCE.guestDaoToGuest(optionalGuestDao.get()));
            }
        }
        return frequentNoShowersList;
    }

    @Override
    public List<Event> findLowAttendanceEvents() {
        List<UUID> lowAttendanceEventsIdList = eventGuestJpaRepository.findLowAttendanceEvents();
        List<Event> lowAttendanceEventsList = new ArrayList<>();
        for (UUID id : lowAttendanceEventsIdList) {
            Optional<EventDao> optionalEventDao = eventJpaRepository.findById(id);
            if (optionalEventDao.isPresent()) {
                lowAttendanceEventsList.add(EventMapperDB.INSTANCE.eventDaoToEvent(optionalEventDao.get()));
            }
        }
        return lowAttendanceEventsList;
    }

    @Override
    public Optional<EventGuest> findEventGuestById(UUID id) {
        EventGuest eventGuest = EventGuestMapperDB.INSTANCE.eventGuestDaoToEventGuest(eventGuestJpaRepository.findById(id).orElse(null));
        return Optional.ofNullable(eventGuest);
    }

    @Override
    public Optional<EventGuest> findEventGuestByEventIdAndGuestId(UUID eventId, UUID guestId) {
        EventGuest eventGuest = EventGuestMapperDB.INSTANCE.eventGuestDaoToEventGuest(eventGuestJpaRepository.findByEventIdAndGuestId(eventId, guestId).orElse(null));
        return Optional.ofNullable(eventGuest);
    }

    @Override
    public EventGuest saveEventGuest(EventGuest eventGuest) {
        EventGuestDao eventGuestDao = EventGuestMapperDB.INSTANCE.eventGuestToEventGuestDao(eventGuest);
        return EventGuestMapperDB.INSTANCE.eventGuestDaoToEventGuest(eventGuestJpaRepository.save(eventGuestDao));
    }

    @Override
    public void deleteEventGuest(UUID id) {
        eventGuestJpaRepository.deleteById(id);
    }

    @Override
    public List<Guest> findAttendedGuestsByTheme(String theme) {
        String tempTheme = "";
        switch (theme) {
            case "Black Tie Gala":
                tempTheme = "BTG";
                break;
            case "Futuristic Banquet":
                tempTheme = "FB";
                break;
            case "80s Retro Night":
                tempTheme = "_8RN";
                break;
            case "Masquerade Ball":
                tempTheme = "MB";
                break;
        }
        List<UUID> guestIdList = eventGuestJpaRepository.findGuestsAttendedAllEventsWithGivenTheme(tempTheme);
        List<Guest> attendedGuestsByThemeList = new ArrayList<>();
        for (UUID id : guestIdList) {
            Optional<GuestDao> optionalGuestDao = guestJpaRepository.findById(id);
            if (optionalGuestDao.isPresent()) {
                attendedGuestsByThemeList.add(GuestMapperDB.INSTANCE.guestDaoToGuest(optionalGuestDao.get()));
            }
        }
        return attendedGuestsByThemeList;
    }
}
