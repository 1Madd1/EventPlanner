package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class EventNotFoundException extends EventPlannerException {
    public EventNotFoundException(String m) {
        super(ErrorCodes.EVENT_NOT_FOUND, m);
    }
}
