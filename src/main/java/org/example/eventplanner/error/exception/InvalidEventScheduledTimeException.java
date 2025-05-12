package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class InvalidEventScheduledTimeException extends EventPlannerException {
    public InvalidEventScheduledTimeException(String m) {
        super(ErrorCodes.INVALID_EVENT_SCHEDULED_TIME, m);
    }
}
