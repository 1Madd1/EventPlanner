package org.example.eventplanner.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventGuest {
    private UUID id;
    private Event event;
    private Guest guest;
    private boolean attending;
    private boolean attended;
}
