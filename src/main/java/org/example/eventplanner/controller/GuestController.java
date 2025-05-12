package org.example.eventplanner.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.dto.GuestDto;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getById(@PathVariable(name = "id") UUID guestId) {
        System.out.println("GuestController.geyById with id: " + guestId + " called");

        Guest guest = guestService.findGuestById(guestId);

        return guest != null ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody GuestDto guestDto) {
        System.out.println("GuestController.createGuest called - " + guestDto);

        if (guestService.findGuestById() ==)

        return new ResponseEntity<>(guestService.createGuest(Guest.builder()
                .email(guestDto.getEmail())
                .firstName(guestDto.getFirstName())
                .lastName(guestDto.getLastName())
                .phoneNumber(guestDto.getPhoneNumber())
                .build()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDto guestDto) {
        System.out.println("GuestController.updateGuest called - " + guestDto);

        return new ResponseEntity<>(guestService.updateGuest(Guest.builder()
                .id(guestDto.getId())
                .email(guestDto.getEmail())
                .firstName(guestDto.getFirstName())
                .lastName(guestDto.getLastName())
                .phoneNumber(guestDto.getPhoneNumber())
                .build()), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteGuestById(@RequestParam UUID guestId) {
        System.out.println("GuestController.deleteGuestById called for guestID - " + guestId);
        guestService.deleteGuest(guestId);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> findAll() {
        System.out.println("GuestController.findAll called");
        return ResponseEntity.ok(guestService.findAllGuests());
    }

    @GetMapping("/find-all-by-name")
    public List<GuestDto> findAllByName(@RequestParam String name) {
        System.out.println("GuestController.findAllByName called with name - " + name);
        return GuestMapperApi.INSTANCE.guestListToGuestDtoList(guestService.findAllByName(name));
    }

    @GetMapping("/find-guest-by-email")
    public GuestDto findGuestByEmail(@RequestParam String email) {
        System.out.println("GuestController.findGuestByEmail called with email -" + email);
        return GuestMapperApi.INSTANCE.guestToGuestDto(guestService.findGuestByEmail(email));
    }

}
