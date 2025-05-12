package org.example.eventplanner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteGuestToEventRequest {
    private UUID eventId;
    private UUID guestId;
}
