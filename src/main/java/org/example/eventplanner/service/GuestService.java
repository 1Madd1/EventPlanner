package org.example.eventplanner.service;

import org.example.eventplanner.dto.GuestDto;
import org.example.eventplanner.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GuestService {

    /***
     *  Create and persist an agent
     *
     * @param guestDto - Newly created guest
     * @return Valid guest object
     */
    Guest createGuest(GuestDto guestDto);

    /***
     *  Update guest data fields
     *
     * @param guestDto - must be a valid guest object with valid id
     * @return Updated Guest object
     */
    Guest updateGuest(GuestDto guestDto);

    /***
     *
     * @param pageable - used for paging and sorting data
     * @return List of all guests
     */
    Page<Guest> findAllGuests(Pageable pageable);

    /**
     *
     * @param guestName - used to search through all existing guests
     * @param pageable - used for paging and sorting data
     * @return List of existing guests that contain given guestName
     */
    Page<Guest> findAllByName(String guestName, Pageable pageable);

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
