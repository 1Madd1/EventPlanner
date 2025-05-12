package org.example.eventplanner.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class FrequentNoShowers {
    private UUID guestId;
    private long confirmedCount;
    private long failedCount;

    public FrequentNoShowers(UUID guestId, long confirmedCount, long failedCount) {
        this.guestId = guestId;
        this.confirmedCount = confirmedCount;
        this.failedCount = failedCount;
    }
}
