package org.example.eventplanner.adapters.persistance.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class LowAttendanceEventsDao {
    private UUID eventId;

    public LowAttendanceEventsDao(UUID eventId) {
        this.eventId = eventId;
    }
}
