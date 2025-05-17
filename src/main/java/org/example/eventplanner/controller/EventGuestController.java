package org.example.eventplanner.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.dto.request.InviteGuestToEventRequest;
import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("event/guest")
@RequiredArgsConstructor
public class EventGuestController {

    private final EventService eventService;

    @PostMapping("/invite-guest-to-event")
    public ResponseEntity<EventGuest> inviteGuestToEvent(@RequestBody InviteGuestToEventRequest inviteGuestToEventRequest) {
        System.out.println("EventGuestController.inviteGuestToEvent called with inviteGuestToEventRequest -" + inviteGuestToEventRequest);
        return new ResponseEntity<>(eventService.inviteGuest(
                inviteGuestToEventRequest.getEventId(),
                inviteGuestToEventRequest.getGuestId()
        ), HttpStatus.OK);
    }

    @GetMapping("/find-all-invited-guests")
    public ResponseEntity<Page<Guest>> findAllInvitedGuestsByEventId(@RequestParam UUID eventId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size,
                                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                                     @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("EventGuestController.findAllInvitedGuestsByEventId called with eventId -" + eventId);
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(eventService.findAllInvitedGuestByEventId(eventId, pageable), HttpStatus.OK);
    }

    @GetMapping("/accept-invite")
    public ResponseEntity<EventGuest> acceptInvite(@RequestParam UUID eventId, @RequestParam UUID guestId) {
        System.out.println("EventGuestController.acceptInvite called with eventId - " + eventId + ", and guestId - " + guestId);
        return new ResponseEntity<>(eventService.acceptInvite(eventId, guestId), HttpStatus.OK);
    }
}
