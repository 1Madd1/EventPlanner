package org.example.eventplanner.adapters.persistance.mapper;

import org.example.eventplanner.adapters.persistance.dao.EventGuestDao;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EventMapperDB.class, GuestMapperDB.class})
public interface EventGuestMapperDB {
    EventGuestMapperDB INSTANCE = Mappers.getMapper(EventGuestMapperDB.class);

    EventGuestDao eventGuestToEventGuestDao(EventGuest eventGuest);

    EventGuest eventGuestDaoToEventGuest(EventGuestDao eventGuestDao);

    List<EventGuestDao> eventGuestListToEventGuestDaoList(List<EventGuest> eventGuestList);

    List<EventGuest> eventGuestDaoListToEventGuestList(List<EventGuestDao> eventGuestDaoList);
}