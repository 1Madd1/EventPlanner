package org.example.eventplanner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity(name = "event_guest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "attending")
    private boolean attending;

    @Column(name = "attended")
    private boolean attended;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Guest guest;
}
