package org.example.eventplanner.adapters.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.adapters.rest.dto.EventDto;
import org.example.eventplanner.adapters.rest.dto.GuestDto;
import org.example.eventplanner.adapters.rest.dto.request.InviteGuestToEventRequest;
import org.example.eventplanner.adapters.rest.mapper.EventMapperApi;
import org.example.eventplanner.adapters.rest.mapper.GuestMapperApi;
import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.usecase.EventUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    
    private final EventUseCase eventUseCase;
    
    @GetMapping("/{id}")
    public EventDto getById(@PathVariable(name = "id") UUID eventId) {
        System.out.println("EventController.geyById with id: " + eventId + " called");

        return EventMapperApi.INSTANCE.eventToEventDto(eventUseCase.findEventById(eventId));
    }
    
    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        System.out.println("EventController.createEvent called - " + eventDto);

        Event createdEvent = eventUseCase.createEvent(EventMapperApi.INSTANCE.eventDtoToEvent(eventDto));
        return EventMapperApi.INSTANCE.eventToEventDto(createdEvent);
    }

    @PutMapping
    public EventDto updateEvent(@RequestBody EventDto eventDto) {
        System.out.println("EventController.updateEvent called - " + eventDto);

        Event updatedEvent = eventUseCase.updateEvent(EventMapperApi.INSTANCE.eventDtoToEvent(eventDto));
        return EventMapperApi.INSTANCE.eventToEventDto(updatedEvent);
    }

    @DeleteMapping
    public void deleteEventById(@RequestParam UUID eventId) {
        System.out.println("EventController.deleteEventById called for eventID - " + eventId);
        eventUseCase.deleteEvent(eventId);
    }

    @GetMapping
    public List<EventDto> findAll() {
        System.out.println("EventController.findAll called");
        return EventMapperApi.INSTANCE.eventListToEventDtoList(eventUseCase.findAllEvents());
    }
    
}
