package org.example.eventplanner.service;

import org.example.eventplanner.dto.EventDto;
import org.example.eventplanner.entity.Event;
import org.example.eventplanner.entity.EventGuest;
import org.example.eventplanner.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EventService {

    /***
     *  Create and persist an agent
     *
     * @param eventDto - Newly created event
     * @return Valid event object
     */
    Event createEvent(EventDto eventDto);

    /***
     *  Update event data fields
     *
     * @param eventDto - must be a valid eventDto object with valid id
     * @return Updated Event object
     */
    Event updateEvent(EventDto eventDto);

    /***
     *
     * @return Page of all events
     */
    Page<Event> findAllEvents(Pageable pageable);

    /***
     *
     * @param eventId - must be a valid id
     * @param pageable - used for paging and sorting data
     * @return Page of all invited guests by given eventId
     */
    Page<Guest> findAllInvitedGuestByEventId(UUID eventId, Pageable pageable);

    /***
     * @param eventId - valid event UUID
     * @return Event with provided id if one exists
     */
    Event findEventById(UUID eventId);

    /***
     * Delete event with specified id
     * @param id - must be a valid
     */
    void deleteEvent(UUID id);

    /**
     *
     * @param eventId - Valid event id that guest is invited to
     * @param guestId - Valid guest id that is invited to a given eventId parameter
     * @return EventGuest object with given eventId and invited guestId
     */
    EventGuest inviteGuest(UUID eventId, UUID guestId);

    /**
     * Notify all invited
     */
    void notifyConfirmedInvitedGuestsForUpcomingEvent();

    /**
     * Guest accepts invite to the event
     * @param eventId - must be a valid eventId
     * @param guestId - must be a valid guestId
     */
    EventGuest acceptInvite(UUID eventId, UUID guestId);

}
