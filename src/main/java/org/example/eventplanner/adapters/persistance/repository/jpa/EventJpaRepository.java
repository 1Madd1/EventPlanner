package org.example.eventplanner.adapters.persistance.repository.jpa;

import org.example.eventplanner.adapters.persistance.dao.EventDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventDao, UUID> {
    List<EventDao> findAllByDateOfEventBetween(LocalDateTime start, LocalDateTime end);
}
