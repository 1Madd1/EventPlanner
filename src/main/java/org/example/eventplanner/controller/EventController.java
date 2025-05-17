package org.example.eventplanner.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.dto.EventDto;
import org.example.eventplanner.entity.Event;
import org.example.eventplanner.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable(name = "id") UUID eventId) {
        System.out.println("EventController.geyById with id: " + eventId + " called");

        return new ResponseEntity<>(eventService.findEventById(eventId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        System.out.println("EventController.createEvent called - " + eventDto);

        Event createdEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody EventDto eventDto) {
        System.out.println("EventController.updateEvent called - " + eventDto);

        Event updatedEvent = eventService.updateEvent(eventDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteEventById(@RequestParam UUID eventId) {
        System.out.println("EventController.deleteEventById called for eventID - " + eventId);
        return eventService.findEventById(eventId) == null ? ResponseEntity.ok().build() : new ResponseEntity<>(HttpStatus.valueOf(409));
    }

    @GetMapping
    public ResponseEntity<Page<Event>> findAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("EventController.findAll called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(eventService.findAllEvents(pageable), HttpStatus.OK);
    }
    
}
