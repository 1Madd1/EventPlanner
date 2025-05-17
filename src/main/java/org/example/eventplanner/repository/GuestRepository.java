package org.example.eventplanner.repository;

import org.example.eventplanner.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<Guest, UUID> {
    Optional<Guest> findByEmail(String email);

    Page<Guest> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
