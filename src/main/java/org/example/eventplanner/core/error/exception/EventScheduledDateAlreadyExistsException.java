package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class EventScheduledDateAlreadyExistsException extends EventPlannerException {
    public EventScheduledDateAlreadyExistsException(String m) {
        super(ErrorCodes.EVENT_SCHEDULED_DATE_ALREADY_EXISTS, m);
    }
}
