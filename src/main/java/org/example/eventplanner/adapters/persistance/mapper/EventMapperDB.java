package org.example.eventplanner.adapters.persistance.mapper;

import org.example.eventplanner.adapters.persistance.dao.EventDao;
import org.example.eventplanner.core.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapperDB {
    EventMapperDB INSTANCE = Mappers.getMapper(EventMapperDB.class);
    
    EventDao eventToEventDao(Event event);
    
    Event eventDaoToEvent(EventDao eventDao);
    
    List<EventDao> eventListToEventDaoList(List<Event> eventList);
    
    List<Event> eventDaoListToEventList(List<EventDao> eventDaoList);
}