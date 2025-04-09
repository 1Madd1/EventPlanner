package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class EventGuestNotFoundException extends EventPlannerException {
    public EventGuestNotFoundException(String m) {
        super(ErrorCodes.EVENT_GUEST_NOT_FOUND, m);
    }
}
