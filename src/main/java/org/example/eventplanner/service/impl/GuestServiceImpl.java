package org.example.eventplanner.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.error.exception.GuestEmailAlreadyExistsException;
import org.example.eventplanner.error.exception.GuestNotFoundException;
import org.example.eventplanner.repository.GuestRepository;
import org.example.eventplanner.service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Builder
@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    
    @Override
    public Guest createGuest(Guest guest) {
        for (Guest g : guestRepository.findAll()) {
            if (guest.getEmail().equals(g.getEmail())) {
                throw new GuestEmailAlreadyExistsException("Guest with email" + guest.getEmail() + " already exists!");
            }
        }
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public List<Guest> findAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public List<Guest> findAllByName(String guestName) {
        return guestRepository.findAllByNameContainingIgnoreCase(guestName);
    }

    @Override
    public Guest findGuestById(UUID guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isEmpty()) {
            throw new GuestNotFoundException("Guest with id " + guestId + " not found!");
        }
        return guest.get();
    }

    @Override
    public Guest findGuestByEmail(String email) {
        Optional<Guest> guest = guestRepository.findByEmail(email);
        if (guest.isEmpty()) {
            throw new GuestNotFoundException("Guest with email " + email + " not found!");
        }
        return guest.get();
    }

    @Override
    public void deleteGuest(UUID id) {
        guestRepository.deleteById(id);
    }
    
}
