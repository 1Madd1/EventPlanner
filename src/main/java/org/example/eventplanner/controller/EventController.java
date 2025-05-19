package org.example.eventplanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "A GET request that returns ResponseEntity<Event> via given id.")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@Parameter(description = "id of an existing Event") @PathVariable(name = "id") UUID eventId) {
        System.out.println("EventController.geyById with id: " + eventId + " called");

        return new ResponseEntity<>(eventService.findEventById(eventId), HttpStatus.OK);
    }

    @Operation(summary = "A POST request that returns ResponseEntity<Event> after creating a new Event via given EventDto as a request body.")
    @PostMapping
    public ResponseEntity<Event> createEvent(@Parameter(description = "an EventDto with all necessary information except id") @RequestBody EventDto eventDto) {
        System.out.println("EventController.createEvent called - " + eventDto);

        Event createdEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Operation(summary = "A PUT request that returns ResponseEntity<Event> after updating existing Event via given EventDto as a request body.")
    @PutMapping
    public ResponseEntity<Event> updateEvent(@Parameter(description = "an EventDto with all necessary information and with an id of an existing Event") @RequestBody EventDto eventDto) {
        System.out.println("EventController.updateEvent called - " + eventDto);

        Event updatedEvent = eventService.updateEvent(eventDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @Operation(summary = "A DELETE request that removes an Event via given id of an existing Event and returns OK if delete was successful, error code 409 if not.")
    @DeleteMapping
    public ResponseEntity deleteEventById(@Parameter(description = "id of an existing Event") @RequestParam UUID eventId) {
        System.out.println("EventController.deleteEventById called for eventID - " + eventId);
        return eventService.findEventById(eventId) == null ? ResponseEntity.ok().build() : new ResponseEntity<>(HttpStatus.valueOf(409));
    }

    @Operation(summary = "A GET request that returns ResponseEntity<Page<Event>> and takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping
    public ResponseEntity<Page<Event>> findAll(@Parameter(description = "on what page to be on ")@RequestParam(defaultValue = "0") int page,
                                               @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                               @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                               @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("EventController.findAll called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(eventService.findAllEvents(pageable), HttpStatus.OK);
    }
    
}
