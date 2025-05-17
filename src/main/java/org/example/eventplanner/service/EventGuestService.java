package org.example.eventplanner.service;

import org.example.eventplanner.dto.EventGuestDto;
import org.example.eventplanner.entity.Event;
import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.Guest;
import org.example.eventplanner.entity.enums.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EventGuestService {
    /***
     *  Create and persist an agent
     *
     * @param eventGuestDto - Newly created EventGuest
     * @return Valid EventGuest object
     */
    EventGuest createEventGuest(EventGuestDto eventGuestDto);

    /***
     *  Update EventGuest data fields
     *
     * @param eventGuestDto - must be a valid eventGuestDto object with valid id
     * @return Updated EventGuest object
     */
    EventGuest updateEventGuest(EventGuestDto eventGuestDto);

    /***
     *
     * @param pageable - used for paging and sorting data
     * @return Page of all EventGuests
     */
    Page<EventGuest> findAllEventGuests(Pageable pageable);

    /***
     * @param eventId - valid event UUID
     * @param pageable - used for paging and sorting data
     * @return Page of all EventGuest filtered by given eventId
     */
    Page<EventGuest> findAllEventGuestsByEventId(UUID eventId, Pageable pageable);

    /***
     *
     * @return List of top 5 confirmed and attended guests
     */
    List<Guest> findTopFiveConfirmedAndAttendedGuests();

    /***
     *
     * @return List of frequent no showing guests
     */
    List<Guest> findFrequentNoShowers(Pageable pageable);

    /***
     *
     * @return List of low attendance events
     */
    Page<Event> findLowAttendanceEvents(Pageable pageable);

    /***
     * @param eventGuestId - valid EventGuest UUID
     * @return EventGuest with provided id if one exists
     */
    EventGuest findEventGuestById(UUID eventGuestId);

    /***
     * @param eventId - valid Event UUID
     * @param guestId - valid Guest UUID
     * @return EventGuest with provided eventId and guestId
     */
    EventGuest findEventGuestByEventIdAndGuestId(UUID eventId, UUID guestId);

    /***
     *
     * @param pageable - used for paging and sorting data
     * @return Page of all EventGuests
     */
    Page<Guest> findAttendedGuestsByTheme(Theme theme, Pageable pageable);

    /***
     * Delete EventGuest with specified id
     * @param id - must be a valid
     */
    void deleteEventGuest(UUID id);

}
