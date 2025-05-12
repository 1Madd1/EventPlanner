package org.example.eventplanner.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Top5Guests {
    private UUID guestId;
    private long attendanceCount;

    public Top5Guests(UUID guestId, long attendanceCount) {
        this.guestId = guestId;
        this.attendanceCount = attendanceCount;
    }
}
