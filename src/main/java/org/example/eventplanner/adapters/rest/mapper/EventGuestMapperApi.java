package org.example.eventplanner.adapters.rest.mapper;

import org.example.eventplanner.adapters.rest.dto.EventGuestDto;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventGuestMapperApi {
    EventGuestMapperApi INSTANCE = Mappers.getMapper(EventGuestMapperApi.class);
    
    EventGuest eventGuestDtoToEventGuest(EventGuestDto eventGuestDto);
    EventGuestDto eventGuestToEventGuestDto(EventGuest eventGuest);
    
    List<EventGuest> eventGuestDtoListToEventGuestList(List<EventGuestDto> eventGuestDtoList);
    List<EventGuestDto> eventGuestListToEventGuestDtoList(List<EventGuest> eventGuestList);
}
