package org.example.eventplanner.adapters.persistance.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class Top5GuestsDao {
    private UUID guestId;
    private long attendanceCount;

    public Top5GuestsDao(UUID guestId, long attendanceCount) {
        this.guestId = guestId;
        this.attendanceCount = attendanceCount;
    }
}
