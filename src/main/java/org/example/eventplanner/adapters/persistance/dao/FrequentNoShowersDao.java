package org.example.eventplanner.adapters.persistance.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class FrequentNoShowersDao {
    private UUID guestId;
    private long confirmedCount;
    private long failedCount;

    public FrequentNoShowersDao(UUID guestId, long confirmedCount, long failedCount) {
        this.guestId = guestId;
        this.confirmedCount = confirmedCount;
        this.failedCount = failedCount;
    }
}
