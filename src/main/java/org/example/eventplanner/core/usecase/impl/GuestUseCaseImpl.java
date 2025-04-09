package org.example.eventplanner.core.usecase.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.error.exception.GuestEmailAlreadyExistsException;
import org.example.eventplanner.core.error.exception.GuestNotFoundException;
import org.example.eventplanner.core.repository.GuestRepository;
import org.example.eventplanner.core.usecase.GuestUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Builder
public class GuestUseCaseImpl implements GuestUseCase {

    private final GuestRepository guestRepository;
    
    @Override
    public Guest createGuest(Guest guest) {
        for (Guest g : guestRepository.findAllGuests()) {
            if (guest.getEmail().equals(g.getEmail())) {
                throw new GuestEmailAlreadyExistsException("Guest with email" + guest.getEmail() + " already exists!");
            }
        }
        return guestRepository.saveGuest(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.saveGuest(guest);
    }

    @Override
    public List<Guest> findAllGuests() {
        return guestRepository.findAllGuests();
    }

    @Override
    public List<Guest> findAllByName(String guestName) {
        return guestRepository.findAllGuestsByName(guestName);
    }

    @Override
    public Guest findGuestById(UUID guestId) {
        Optional<Guest> guest = guestRepository.findGuestById(guestId);
        if (guest.isEmpty()) {
            throw new GuestNotFoundException("Guest with id " + guestId + " not found!");
        }
        return guest.get();
    }

    @Override
    public Guest findGuestByEmail(String email) {
        Optional<Guest> guest = guestRepository.findGuestByEmail(email);
        if (guest.isEmpty()) {
            throw new GuestNotFoundException("Guest with email " + email + " not found!");
        }
        return guest.get();
    }

    @Override
    public void deleteGuest(UUID id) {
        guestRepository.deleteGuest(id);
    }
    
}
