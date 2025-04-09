package org.example.eventplanner.adapters.persistance.mapper;

import org.example.eventplanner.adapters.persistance.dao.GuestDao;
import org.example.eventplanner.core.domain.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GuestMapperDB {
    GuestMapperDB INSTANCE = Mappers.getMapper(GuestMapperDB.class);

    GuestDao guestToGuestDao(Guest guest);

    Guest guestDaoToGuest(GuestDao guestDao);

    List<GuestDao> guestListToGuestDaoList(List<Guest> guestList);

    List<Guest> guestDaoListToGuestList(List<GuestDao> guestDaoList);
}