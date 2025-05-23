package org.example.eventplanner.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.example.eventplanner.repository.EventGuestRepository;
import org.example.eventplanner.repository.EventRepository;
import org.example.eventplanner.repository.GuestRepository;
import org.example.eventplanner.service.EventGuestService;
import org.example.eventplanner.service.EventService;
import org.example.eventplanner.service.GuestService;
import org.example.eventplanner.service.impl.EventGuestServiceImpl;
import org.example.eventplanner.service.impl.EventServiceImpl;
import org.example.eventplanner.service.impl.GuestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    EventService eventService(EventRepository eventRepository,
                              GuestRepository guestRepository,
                              EventGuestRepository eventGuestRepository) {
        return EventServiceImpl.builder()
                .eventRepository(eventRepository)
                .guestRepository(guestRepository)
                .eventGuestRepository(eventGuestRepository)
                .build();
    }

    @Bean
    GuestService guestService(GuestRepository guestRepository,
                              EventGuestRepository eventGuestRepository) {
        return GuestServiceImpl.builder()
                .guestRepository(guestRepository)
                .eventGuestRepository(eventGuestRepository)
                .build();
    }

    @Bean
    EventGuestService eventGuestService(EventRepository eventRepository,
                                        GuestRepository guestRepository,
                                        EventGuestRepository eventGuestRepository) {
        return EventGuestServiceImpl.builder()
                .eventRepository(eventRepository)
                .guestRepository(guestRepository)
                .eventGuestRepository(eventGuestRepository)
                .build();
    }

}
