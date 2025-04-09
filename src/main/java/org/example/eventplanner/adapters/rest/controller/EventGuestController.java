package org.example.eventplanner.adapters.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.adapters.rest.dto.EventGuestDto;
import org.example.eventplanner.adapters.rest.dto.GuestDto;
import org.example.eventplanner.adapters.rest.dto.request.InviteGuestToEventRequest;
import org.example.eventplanner.adapters.rest.mapper.EventGuestMapperApi;
import org.example.eventplanner.adapters.rest.mapper.GuestMapperApi;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.usecase.EventUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("event/guest")
@RequiredArgsConstructor
public class EventGuestController {

    private final EventUseCase eventUseCase;

    @PostMapping("/invite-guest-to-event")
    public EventGuest inviteGuestToEvent(@RequestBody InviteGuestToEventRequest inviteGuestToEventRequest) {
        System.out.println("EventGuestController.inviteGuestToEvent called with inviteGuestToEventRequest -" + inviteGuestToEventRequest);
        return eventUseCase.inviteGuest(
                inviteGuestToEventRequest.getEventId(),
                inviteGuestToEventRequest.getGuestId()
        );
    }

    @GetMapping("/find-all-invited-guests")
    public List<GuestDto> findAllInvitedGuestsByEventId(@RequestParam UUID eventId) {
        System.out.println("EventGuestController.findAllInvitedGuestsByEventId called with eventId -" + eventId);
        return GuestMapperApi.INSTANCE.guestListToGuestDtoList(
                eventUseCase.findAllInvitedGuestByEventId(eventId)
        );
    }

    @GetMapping("/accept-invite")
    public EventGuestDto acceptInvite(@RequestParam Map<String, String> paramMap) {
        UUID eventId = UUID.fromString(paramMap.get("eventId"));
        UUID guestId = UUID.fromString(paramMap.get("guestId"));
        System.out.println("EventGuestController.acceptInvite called with eventId - " + eventId + ", and guestId - " + guestId);
        return EventGuestMapperApi.INSTANCE.eventGuestToEventGuestDto(eventUseCase.acceptInvite(eventId, guestId));
    }
}
