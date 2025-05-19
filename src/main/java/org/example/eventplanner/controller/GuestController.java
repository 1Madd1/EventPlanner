package org.example.eventplanner.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.eventplanner.dto.GuestDto;
import org.example.eventplanner.entity.Guest;
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

    @ApiResponse(responseCode = "200", description = "Returns a Guest with a given id of an existing guest",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GuestDto.class))})
    @Operation(summary = "A GET request that returns ResponseEntity<Guest> via given id.")
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getById(@Parameter(description = "id of an existing Guest") @PathVariable(name = "id") UUID guestId) {
        System.out.println("GuestController.geyById with id: " + guestId + " called");

        Guest guest = guestService.findGuestById(guestId);

        return guest != null ? ResponseEntity.ok(guest) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "A POST request that returns ResponseEntity<Guest> after creating a new Guest via given GuestDto as a request body.")
    @PostMapping
    public ResponseEntity<Guest> createGuest(@Parameter(description = "a GuestDto with all necessary information without an id") @RequestBody GuestDto guestDto) {
        System.out.println("GuestController.createGuest called - " + guestDto);

        return new ResponseEntity<>(guestService.createGuest(guestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "A PUT request that returns ResponseEntity<Guest> after updating existing Guest via given GuestDto as a request body.")
    @PutMapping
    public ResponseEntity<Guest> updateGuest(@Parameter(description = "a GuestDto with all necessary information and with an id of an existing Guest") @RequestBody GuestDto guestDto) {
        System.out.println("GuestController.updateGuest called - " + guestDto);

        return new ResponseEntity<>(guestService.updateGuest(guestDto), HttpStatus.OK);
    }

    @Operation(summary = "A DELETE request that removes a Guest via given id of an existing Guest and returns OK if delete was successful, error code 409 if not.")
    @DeleteMapping
    public ResponseEntity deleteGuestById(@Parameter(description = "id of an existing Guest") @RequestParam UUID guestId) {
        System.out.println("GuestController.deleteGuestById called for guestID - " + guestId);
        guestService.deleteGuest(guestId);
        return guestService.findGuestById(guestId) == null ? ResponseEntity.ok().build() : new ResponseEntity<>(HttpStatus.valueOf(409));
    }

    @Operation(summary = "A GET request that returns ResponseEntity<Page<Guest>> and takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping
    public ResponseEntity<Page<Guest>> findAll(@Parameter(description = "on what page to be on ") @RequestParam(defaultValue = "0") int page,
                                               @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                               @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                               @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("GuestController.findAll called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(guestService.findAllGuests(pageable));
    }

    @Operation(summary = "A GET request that returns ResponseEntity<Page<Guest>> via given firstName and takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping("/find-all-by-name")
    public ResponseEntity<Page<Guest>> findAllByFirstName(@Parameter(description = "used for finding an existing Guest that contains givens first name") @RequestParam String firstName,
                                                          @Parameter(description = "on what page to be on ") @RequestParam(defaultValue = "0") int page,
                                                          @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                                          @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                                          @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("GuestController.findAllByName called with first name - " + firstName);
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(guestService.findAllByFirstName(firstName, pageable), HttpStatus.OK);
    }

    @Operation(summary = "A GET request that returns ResponseEntity<Guest> via given email.")
    @GetMapping("/find-guest-by-email")
    public ResponseEntity<Guest> findGuestByEmail(@Parameter(description = "used for finding an existing Guest that contains givens email") @RequestParam String email) {
        System.out.println("GuestController.findGuestByEmail called with email -" + email);
        return new ResponseEntity<>(guestService.findGuestByEmail(email), HttpStatus.OK);
    }

}
