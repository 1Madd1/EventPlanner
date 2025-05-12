package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class EventScheduledDateAlreadyExistsException extends EventPlannerException {
    public EventScheduledDateAlreadyExistsException(String m) {
        super(ErrorCodes.EVENT_SCHEDULED_DATE_ALREADY_EXISTS, m);
    }
}
