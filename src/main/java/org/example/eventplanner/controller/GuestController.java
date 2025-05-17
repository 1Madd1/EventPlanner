package org.example.eventplanner.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.dto.GuestDto;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.service.EventGuestService;
import org.example.eventplanner.service.GuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return new ResponseEntity<>(guestService.createGuest(guestDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDto guestDto) {
        System.out.println("GuestController.updateGuest called - " + guestDto);

        return new ResponseEntity<>(guestService.updateGuest(guestDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteGuestById(@RequestParam UUID guestId) {
        System.out.println("GuestController.deleteGuestById called for guestID - " + guestId);
        guestService.deleteGuest(guestId);
        return guestService.findGuestById(guestId) == null ? ResponseEntity.ok().build() : new ResponseEntity<>(HttpStatus.valueOf(409));
    }

    @GetMapping
    public ResponseEntity<Page<Guest>> findAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size,
                                               @RequestParam(defaultValue = "id") String sortBy,
                                               @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("GuestController.findAll called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(guestService.findAllGuests(pageable));
    }

    @GetMapping("/find-all-by-name")
    public ResponseEntity<Page<Guest>> findAllByName(@RequestParam String name,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("GuestController.findAllByName called with name - " + name);
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(guestService.findAllByName(name, pageable), HttpStatus.OK);
    }

    @GetMapping("/find-guest-by-email")
    public ResponseEntity<Guest> findGuestByEmail(@RequestParam String email) {
        System.out.println("GuestController.findGuestByEmail called with email -" + email);
        return new ResponseEntity<>(guestService.findGuestByEmail(email), HttpStatus.OK);
    }

}
