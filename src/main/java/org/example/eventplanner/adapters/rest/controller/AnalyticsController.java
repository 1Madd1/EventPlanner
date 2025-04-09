package org.example.eventplanner.adapters.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.core.domain.entity.Event;
import org.example.eventplanner.core.domain.entity.EventGuest;
import org.example.eventplanner.core.domain.entity.Guest;
import org.example.eventplanner.core.repository.EventGuestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final EventGuestRepository eventGuestRepository;

    @GetMapping("/find-top-5-confirmed-and-attended-guests")
    public List<Guest> getTop5ConfirmedAndAttendedGuests() {
        System.out.println("AnalyticsController.findTop5ConfirmedAndAttendedGuests called");
        return eventGuestRepository.findTopFiveConfirmedAndAttendedEventGuests();
    }

    @GetMapping("/find-frequent-no-showers")
    public List<Guest> getFrequentNoShowerGuests() {
        System.out.println("AnalyticsController.getFrequentNoShowerGuests called");
        return eventGuestRepository.findFrequentNoShowers();
    }

    @GetMapping("/find-low-attendance-events")
    public List<Event> getLowAttendanceEvents() {
        System.out.println("AnalyticsController.getLowAttendanceEvents called");
        return eventGuestRepository.findLowAttendanceEvents();
    }

    @GetMapping("/find-attended-guests-by-theme")
    public List<Guest> getAttendedGuestsByTheme(@RequestParam String theme) {
        System.out.println("AnalyticsController.getAttendedGuestsByTheme called");
        return eventGuestRepository.findAttendedGuestsByTheme(theme);
    }
}
