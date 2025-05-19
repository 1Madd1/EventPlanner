package org.example.eventplanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "A GET request that returns a list of top 5 guests that confirmed going to an Event and attending that Event.")
    @GetMapping("/find-top-5-confirmed-and-attended-guests")
    public List<Guest> getTop5ConfirmedAndAttendedGuests() {
        System.out.println("AnalyticsController.findTop5ConfirmedAndAttendedGuests called");
        return eventGuestService.findTopFiveConfirmedAndAttendedGuests();
    }

    @Operation(summary = "A GET request that returns Page<Guest> of all guests that have said to attend an Event and hadn't attended more than 2 times and request takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping("/find-frequent-no-showers")
    public Page<Guest> getFrequentNoShowerGuests(@Parameter(description = "on what page to be on ")@RequestParam(defaultValue = "0") int page,
                                                 @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                                 @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                                 @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getFrequentNoShowerGuests called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findFrequentNoShowers(pageable);
    }

    @Operation(summary = "A GET request that returns Page<Event> of all upcoming events that have less than 5 guests who confirmed attendance at the Event and request takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping("/find-low-attendance-events")
    public Page<Event> getLowAttendanceEvents(@Parameter(description = "on what page to be on ")@RequestParam(defaultValue = "0") int page,
                                              @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                              @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                              @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getLowAttendanceEvents called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findLowAttendanceEvents(pageable);
    }

    @Operation(summary = "A GET request that returns Page<Guest> of all guests that have attended every Event with a given theme and request takes request params page, size, sortBy and ascending which are optional.")
    @GetMapping("/find-attended-guests-by-theme")
    public Page<Guest> getAttendedGuestsByTheme(@Parameter(description = "an existing Event theme (e.g. 'Black Tie Gala).')") @RequestParam String theme,
                                                @Parameter(description = "on what page to be on ")@RequestParam(defaultValue = "0") int page,
                                                @Parameter(description = "size of a single page") @RequestParam(defaultValue = "5") int size,
                                                @Parameter(description = "in what field the page object needs to sort by (e.g. title, theme, status...)") @RequestParam(defaultValue = "id") String sortBy,
                                                @Parameter(description = "an ascending order if true, descending order if false") @RequestParam(defaultValue = "true") boolean ascending) {
        System.out.println("AnalyticsController.getAttendedGuestsByTheme called");
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return eventGuestService.findAttendedGuestsByTheme(Theme.valueOf(theme), pageable);
    }
}
