package org.example.eventplanner.adapters.persistance.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class EventStatsDao {
    private UUID eventId;
    private long attendanceCount;

    public EventStatsDao(UUID eventId, long attendanceCount) {
        this.eventId = eventId;
        this.attendanceCount = attendanceCount;
    }
}
