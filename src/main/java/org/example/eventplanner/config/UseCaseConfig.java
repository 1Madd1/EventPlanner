package org.example.eventplanner.config;

import org.example.eventplanner.core.repository.EventGuestRepository;
import org.example.eventplanner.core.repository.EventRepository;
import org.example.eventplanner.core.repository.GuestRepository;
import org.example.eventplanner.core.usecase.EventUseCase;
import org.example.eventplanner.core.usecase.GuestUseCase;
import org.example.eventplanner.core.usecase.impl.EventUseCaseImpl;
import org.example.eventplanner.core.usecase.impl.GuestUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    EventUseCase eventUseCase(EventRepository eventRepository,
                              GuestRepository guestRepository,
                              EventGuestRepository eventGuestRepository) {
        return EventUseCaseImpl.builder()
                .eventRepository(eventRepository)
                .guestRepository(guestRepository)
                .eventGuestRepository(eventGuestRepository)
                .build();
    }

    @Bean
    GuestUseCase guestUseCase(GuestRepository guestRepository) {
        return GuestUseCaseImpl.builder()
                .guestRepository(guestRepository)
                .build();
    }

}
