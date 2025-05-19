package org.example.eventplanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "A POST request for inviting a Guest to an Event and returns ResponseEntity<EventGuest> after creating a new EventGuest via given InviteGuestToEventRequest as a request body which has ids of Guest and Event.")
    @PostMapping("/invite-guest-to-event")
    public ResponseEntity<EventGuest> inviteGuestToEvent(@Parameter(description = "an InviteGuestToEventRequest object that acts as a Dto object which contains eventId and guestId in itself") @RequestBody InviteGuestToEventRequest inviteGuestToEventRequest) {
        System.out.println("EventGuestController.inviteGuestToEvent called with inviteGuestToEventRequest -" + inviteGuestToEventRequest);
        return new ResponseEntity<>(eventService.inviteGuest(
                inviteGuestToEventRequest.getEventId(),
                inviteGuestToEventRequest.getGuestId()
        ), HttpStatus.OK);
    }

    @Operation(summary = "A GET request for finding all invited guests of an Event and returns ResponseEntity<Page<Guest>> via given eventId and takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping("/find-all-invited-guests")
    public ResponseEntity<Page<Guest>> findAllInvitedGuestsByEventId(@Parameter(description = "id of an existing Event") @RequestParam UUID eventId,
                                                                     @Parameter(description = "on what page to be on ")@RequestParam(defaultValue = "0") int page,
                                                                     @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                                                     @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                                                     @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("EventGuestController.findAllInvitedGuestsByEventId called with eventId -" + eventId);
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(eventService.findAllInvitedGuestByEventId(eventId, pageable), HttpStatus.OK);
    }

    @Operation(summary = "A GET request for an invited Guest to accept an invite to an Event and returns ResponseEntity<EventGuest> via given eventId and guestId.")
    @GetMapping("/accept-invite")
    public ResponseEntity<EventGuest> acceptInvite(@Parameter(description = "id of an existing Event") @RequestParam UUID eventId,
                                                   @Parameter(description = "id of an existing Guest") @RequestParam UUID guestId) {
        System.out.println("EventGuestController.acceptInvite called with eventId - " + eventId + ", and guestId - " + guestId);
        return new ResponseEntity<>(eventService.acceptInvite(eventId, guestId), HttpStatus.OK);
    }
}
