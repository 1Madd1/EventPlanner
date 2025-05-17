package org.example.eventplanner.controller;

import lombok.RequiredArgsConstructor;
import org.example.eventplanner.entity.Event;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.entity.enums.Theme;
import org.example.eventplanner.service.EventGuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final EventGuestService eventGuestService;

    @GetMapping("/find-top-5-confirmed-and-attended-guests")
    public List<Guest> getTop5ConfirmedAndAttendedGuests() {
        System.out.println("AnalyticsController.findTop5ConfirmedAndAttendedGuests called");
        return eventGuestService.findTopFiveConfirmedAndAttendedGuests();
    }

    @GetMapping("/find-frequent-no-showers")
    public List<Guest> getFrequentNoShowerGuests(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getFrequentNoShowerGuests called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findFrequentNoShowers(pageable);
    }

    @GetMapping("/find-low-attendance-events")
    public Page<Event> getLowAttendanceEvents(@RequestParam String theme,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size,
                                              @RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getLowAttendanceEvents called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findLowAttendanceEvents(pageable);
    }

    @GetMapping("/find-attended-guests-by-theme")
    public Page<Guest> getAttendedGuestsByTheme(@RequestParam String theme,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "id") String sortBy,
                                                @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getAttendedGuestsByTheme called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findAttendedGuestsByTheme(Theme.valueOf(theme), pageable);
    }
}
