package org.example.eventplanner.adapters.rest.mapper;

import org.example.eventplanner.adapters.rest.dto.EventDto;
import org.example.eventplanner.core.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapperApi {
    EventMapperApi INSTANCE = Mappers.getMapper(EventMapperApi.class);

    Event eventDtoToEvent(EventDto eventDto);
    EventDto eventToEventDto(Event event);

    List<Event> eventDtoListToEventList(List<EventDto> eventDtoList);
    List<EventDto> eventListToEventDtoList(List<Event> eventList);
}
