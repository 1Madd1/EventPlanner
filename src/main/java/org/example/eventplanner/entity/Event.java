package org.example.eventplanner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventplanner.entity.enums.Status;
import org.example.eventplanner.entity.enums.Theme;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "event")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme")
    private Theme theme;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "location")
    private String location;

    @Column(name = "date_of_event")
    private LocalDateTime dateOfEvent;
}
