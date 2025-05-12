package org.example.eventplanner.repository;

import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.FrequentNoShowers;
import org.example.eventplanner.entity.Top5Guests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventGuestRepository extends JpaRepository<EventGuest, UUID> {
    List<EventGuest> findAllByEventIdAndAttendingTrue(UUID eventId);

    Optional<EventGuest> findByEventIdAndGuestId(UUID eventId, UUID guestId);

    List<EventGuest> findAllByEventId(UUID eventId);

    @Query(value = """
    SELECT guest_id, COUNT(*) AS attendanceCount FROM event_guest
    WHERE attending = true AND attended = true
    GROUP BY guest_id
    ORDER BY COUNT(*) DESC
    LIMIT 5;
    """, nativeQuery = true)
    List<Top5Guests> findTop5ConfirmedAttendedGuests();

    @Query(value = """
    SELECT eg.guest_id, COUNT(*) AS confirmed_count,
    SUM(CASE WHEN eg.attended = FALSE THEN 1 ELSE 0 END) AS failed_count
    FROM event_guest eg
    JOIN guest g ON eg.guest_id = g.id
    WHERE eg.attending = TRUE
    GROUP BY eg.guest_id
    HAVING COUNT(*) >= 3
    AND SUM(CASE WHEN eg.attended = FALSE THEN 1 ELSE 0 END) >= 2;
    """, nativeQuery = true)
    List<FrequentNoShowers> findFrequentNoShowers();

    @Query(value = """
    SELECT e.id
    FROM event e
    JOIN event_guest eg ON e.id = eg.event_id
    WHERE e.status = 'UPCOMING'
    AND eg.attending = TRUE
    GROUP BY e.id
    HAVING COUNT(eg.guest_id) < 5;
    """, nativeQuery = true)
    List<UUID> findLowAttendanceEvents();

    @Query("""
    SELECT g.id
    FROM guest g
    WHERE EXISTS (
        SELECT 1
        FROM event e
        JOIN event_guest eg ON e.id = eg.event.id
        WHERE e.theme = :theme
        AND eg.guest.id = g.id
    )
    AND NOT EXISTS (
        SELECT 1
        FROM event e
        JOIN event_guest eg ON e.id = eg.event.id
        WHERE e.theme = :theme
        AND eg.guest.id = g.id
        AND eg.attended = FALSE
    )
""")
    List<UUID> findGuestsAttendedAllEventsWithGivenTheme(String theme);


}
