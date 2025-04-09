package org.example.eventplanner.core.repository;

import org.example.eventplanner.core.domain.entity.Guest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestRepository {
    List<Guest> findAllGuests();

    List<Guest> findAllGuestsByName(String name);
    
    Optional<Guest> findGuestById(UUID id);

    Optional<Guest> findGuestByEmail(String email);

    Guest saveGuest(Guest guest);
    
    void deleteGuest(UUID id);
}
