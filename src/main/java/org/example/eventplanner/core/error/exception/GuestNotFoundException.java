package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class GuestNotFoundException extends EventPlannerException {
    public GuestNotFoundException(String m) {
        super(ErrorCodes.GUEST_NOT_FOUND, m);
    }
}
