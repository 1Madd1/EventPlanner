package org.example.eventplanner.adapters.persistance.repository.jpa;

import org.example.eventplanner.adapters.persistance.dao.GuestDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestJpaRepository extends JpaRepository<GuestDao, UUID> {
    Optional<GuestDao> findByEmail(String email);
    List<GuestDao> findAllByNameContainingIgnoreCase(String name);
}
