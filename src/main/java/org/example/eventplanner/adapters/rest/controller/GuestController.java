package org.example.eventplanner.adapters.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.adapters.rest.dto.GuestDto;
import org.example.eventplanner.adapters.rest.mapper.GuestMapperApi;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.usecase.GuestUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("guest")
@RequiredArgsConstructor
public class GuestController {
    
    private final GuestUseCase guestUseCase;
    
    @GetMapping("/{id}")
    public GuestDto getById(@PathVariable(name = "id") UUID guestId) {
        System.out.println("GuestController.geyById with id: " + guestId + " called");

        return GuestMapperApi.INSTANCE.guestToGuestDto(guestUseCase.findGuestById(guestId));
    }
    
    @PostMapping
    public GuestDto createGuest(@RequestBody GuestDto guestDto) {
        System.out.println("GuestController.createGuest called - " + guestDto);
        
        Guest createdGuest = guestUseCase.createGuest(GuestMapperApi.INSTANCE.guestDtoToGuest(guestDto));
        return GuestMapperApi.INSTANCE.guestToGuestDto(createdGuest);
    }
    
    @PutMapping
    public GuestDto updateGuest(@RequestBody GuestDto guestDto) {
        System.out.println("GuestController.updateGuest called - " + guestDto);
        
        Guest updatedGuest = guestUseCase.updateGuest(GuestMapperApi.INSTANCE.guestDtoToGuest(guestDto));
        return GuestMapperApi.INSTANCE.guestToGuestDto(updatedGuest);
    }
    
    @DeleteMapping
    public void deleteGuestById(@RequestParam UUID guestId) {
        System.out.println("GuestController.deleteGuestById called for guestID - " + guestId);
        guestUseCase.deleteGuest(guestId);
    }
    
    @GetMapping
    public List<GuestDto> findAll() {
        System.out.println("GuestController.findAll called");
        return GuestMapperApi.INSTANCE.guestListToGuestDtoList(guestUseCase.findAllGuests());
    }

    @GetMapping("/find-all-by-name")
    public List<GuestDto> findAllByName(@RequestParam String name) {
        System.out.println("GuestController.findAllByName called with name - " + name);
        return GuestMapperApi.INSTANCE.guestListToGuestDtoList(guestUseCase.findAllByName(name));
    }

    @GetMapping("/find-guest-by-email")
    public GuestDto findGuestByEmail(@RequestParam String email) {
        System.out.println("GuestController.findGuestByEmail called with email -" + email);
        return GuestMapperApi.INSTANCE.guestToGuestDto(guestUseCase.findGuestByEmail(email));
    }
    
}
