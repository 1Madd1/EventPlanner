package org.example.eventplanner.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventplanner.dto.GuestDto;
import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.error.exception.EntityNotFoundException;
import org.example.eventplanner.error.exception.GuestEmailAlreadyExistsException;
import org.example.eventplanner.repository.EventGuestRepository;
import org.example.eventplanner.repository.GuestRepository;
import org.example.eventplanner.service.GuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Builder
@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final EventGuestRepository eventGuestRepository;
    
    @Override
    public Guest createGuest(GuestDto guestDto) {
        if (guestRepository.findByEmail(guestDto.getEmail()).isPresent()) {
            throw new EntityNotFoundException("Guest with email" + guestDto.getEmail() + " already exists!");
        }

        return guestRepository.save(Guest.builder()
                .email(guestDto.getEmail())
                .firstName(guestDto.getFirstName())
                .lastName(guestDto.getLastName())
                .phoneNumber(guestDto.getPhoneNumber())
                .build());
    }

    @Override
    public Guest updateGuest(GuestDto guestDto) {
        return guestRepository.save(Guest.builder()
                .email(guestDto.getEmail())
                .firstName(guestDto.getFirstName())
                .lastName(guestDto.getLastName())
                .phoneNumber(guestDto.getPhoneNumber())
                .build());
    }

    @Override
    public Page<Guest> findAllGuests(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }

    @Override
    public Page<Guest> findAllByName(String guestName, Pageable pageable) {
        return guestRepository.findAllByNameContainingIgnoreCase(guestName, pageable);
    }

    @Override
    public Guest findGuestById(UUID guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isEmpty()) {
            throw new EntityNotFoundException("Guest with id " + guestId + " not found!");
        }
        return guest.get();
    }

    @Override
    public Guest findGuestByEmail(String email) {
        Optional<Guest> guest = guestRepository.findByEmail(email);
        if (guest.isEmpty()) {
            throw new EntityNotFoundException("Guest with email " + email + " not found!");
        }
        return guest.get();
    }

    @Override
    public void deleteGuest(UUID id) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<EventGuest> eventGuestPage = eventGuestRepository.findAllByGuestId(id, pageable);
        for (EventGuest eventGuest : eventGuestPage) {
            eventGuestRepository.deleteById(eventGuest.getId());
        }
        guestRepository.deleteById(id);
    }
    
}
