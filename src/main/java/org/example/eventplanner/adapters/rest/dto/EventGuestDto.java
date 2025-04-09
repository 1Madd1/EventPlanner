package org.example.eventplanner.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventGuestDto {
    private UUID id;
    private UUID eventId;
    private UUID guestId;
    private boolean attending;
    private boolean attended;
}
