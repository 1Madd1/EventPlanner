package org.example.eventplanner.adapters.persistance.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "event_guest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventGuestDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "attending")
    private boolean attending;

    @Column(name = "attended")
    private boolean attended;

    @ManyToOne
    private GuestDao guest;

    @ManyToOne
    private EventDao event;
}
