package org.example.eventplanner.adapters.persistance.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "location")
    private String location;

    @Column(name = "date_of_event")
    private LocalDateTime dateOfEvent;
}
