package org.example.eventplanner.config;

import org.example.eventplanner.adapters.persistance.repository.EventGuestRepositoryImpl;
import org.example.eventplanner.adapters.persistance.repository.EventRepositoryImpl;
import org.example.eventplanner.adapters.persistance.repository.GuestRepositoryImpl;
import org.example.eventplanner.adapters.persistance.repository.jpa.EventGuestJpaRepository;
import org.example.eventplanner.adapters.persistance.repository.jpa.EventJpaRepository;
import org.example.eventplanner.adapters.persistance.repository.jpa.GuestJpaRepository;
import org.example.eventplanner.core.repository.EventGuestRepository;
import org.example.eventplanner.core.repository.EventRepository;
import org.example.eventplanner.core.repository.GuestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    EventRepository eventRepository(EventJpaRepository eventJpaRepository) {
        return EventRepositoryImpl.builder()
                .eventJpaRepository(eventJpaRepository)
                .build();
    }

    @Bean
    GuestRepository guestRepository(GuestJpaRepository guestJpaRepository) {
        return GuestRepositoryImpl.builder()
                .guestJpaRepository(guestJpaRepository)
                .build();
    }

    @Bean
    EventGuestRepository eventGuestRepository(EventGuestJpaRepository eventGuestJpaRepository,
                                              GuestJpaRepository guestJpaRepository,
                                              EventJpaRepository eventJpaRepository) {
        return EventGuestRepositoryImpl.builder()
                .eventGuestJpaRepository(eventGuestJpaRepository)
                .guestJpaRepository(guestJpaRepository)
                .eventJpaRepository(eventJpaRepository)
                .build();
    }

}
