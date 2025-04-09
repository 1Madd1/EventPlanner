package org.example.eventplanner.adapters.persistance.repository;

import lombok.Builder;
import org.example.eventplanner.adapters.persistance.dao.GuestDao;
import org.example.eventplanner.adapters.persistance.mapper.GuestMapperDB;
import org.example.eventplanner.adapters.persistance.repository.jpa.GuestJpaRepository;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.repository.GuestRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Builder
public class GuestRepositoryImpl implements GuestRepository {

    private final GuestJpaRepository guestJpaRepository;

    @Override
    public List<Guest> findAllGuests() {
        return GuestMapperDB.INSTANCE.guestDaoListToGuestList(guestJpaRepository.findAll());
    }

    @Override
    public List<Guest> findAllGuestsByName(String name) {
        return GuestMapperDB.INSTANCE.guestDaoListToGuestList(guestJpaRepository.findAllByNameContainingIgnoreCase(name));
    }

    @Override
    public Optional<Guest> findGuestById(UUID id) {
        Guest guest = GuestMapperDB.INSTANCE.guestDaoToGuest(guestJpaRepository.findById(id).orElse(null));
        return Optional.ofNullable(guest);
    }

    @Override
    public Optional<Guest> findGuestByEmail(String email) {
        Guest guest = GuestMapperDB.INSTANCE.guestDaoToGuest(guestJpaRepository.findByEmail(email).orElse(null));
        return Optional.ofNullable(guest);
    }

    @Override
    public Guest saveGuest(Guest guest) {
        GuestDao guestDao = GuestMapperDB.INSTANCE.guestToGuestDao(guest);
        return GuestMapperDB.INSTANCE.guestDaoToGuest(guestJpaRepository.save(guestDao));
    }

    @Override
    public void deleteGuest(UUID id) {
        guestJpaRepository.deleteById(id);
    }
}
