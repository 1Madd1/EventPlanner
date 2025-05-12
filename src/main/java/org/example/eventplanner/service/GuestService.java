package org.example.eventplanner.service;

import org.example.eventplanner.entity.Guest;

import java.util.List;
import java.util.UUID;

public interface GuestService {

    /***
     *  Create and persist an agent
     *
     * @param guest - Newly created guest
     * @return Valid guest object
     */
    Guest createGuest(Guest guest);

    /***
     *  Update guest data fields
     *
     * @param guest - must be a valid guest object with valid id
     * @return Updated Guest object
     */
    Guest updateGuest(Guest guest);

    /***
     *
     * @return List of all guests
     */
    List<Guest> findAllGuests();

    /**
     *
     * @param guestName - used to search through all existing guests
     * @return List of existing guests that contain given guestName
     */
    List<Guest> findAllByName(String guestName);

    /***
     * @param guestId - valid guest UUID
     * @return Guest with provided id if one exists
     */
    Guest findGuestById(UUID guestId);

    /***
     *
     * @param email - address of an existing guest
     * @return Guest with provided email if one exists
     */
    Guest findGuestByEmail(String email);

    /***
     * Delete guest with specified id
     * @param id - must be a valid
     */
    void deleteGuest(UUID id);
}
