package org.example.eventplanner.adapters.rest.mapper;

import org.example.eventplanner.adapters.rest.dto.GuestDto;
import org.example.eventplanner.core.domain.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GuestMapperApi {
    GuestMapperApi INSTANCE = Mappers.getMapper(GuestMapperApi.class);
    
    Guest guestDtoToGuest(GuestDto guestDto);
    GuestDto guestToGuestDto(Guest guest);
    
    List<Guest> guestDtoListToGuestList(List<GuestDto> guestDtoList);
    List<GuestDto> guestListToGuestDtoList(List<Guest> guestList);
}
