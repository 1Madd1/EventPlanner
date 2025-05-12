package org.example.eventplanner.repository;

import org.example.eventplanner.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<Guest, UUID> {
    Optional<Guest> findByEmail(String email);
    List<Guest> findAllByNameContainingIgnoreCase(String name);
}
