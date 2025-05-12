package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class EventAndGuestMustExistException extends EventPlannerException {
    public EventAndGuestMustExistException(String m) {
        super(ErrorCodes.EVENT_AND_GUEST_MUST_EXIST, m);
    }
}
