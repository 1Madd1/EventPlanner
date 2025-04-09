package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class InvalidEventScheduledTimeException extends EventPlannerException {
    public InvalidEventScheduledTimeException(String m) {
        super(ErrorCodes.INVALID_EVENT_SCHEDULED_TIME, m);
    }
}
