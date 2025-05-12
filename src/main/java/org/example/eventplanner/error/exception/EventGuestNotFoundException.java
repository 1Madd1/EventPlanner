package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class EventGuestNotFoundException extends EventPlannerException {
    public EventGuestNotFoundException(String m) {
        super(ErrorCodes.EVENT_GUEST_NOT_FOUND, m);
    }
}
