package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class EventAndGuestMustExistException extends EventPlannerException {
    public EventAndGuestMustExistException(String m) {
        super(ErrorCodes.EVENT_AND_GUEST_MUST_EXIST, m);
    }
}
