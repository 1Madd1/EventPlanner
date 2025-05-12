package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class GuestNotFoundException extends EventPlannerException {
    public GuestNotFoundException(String m) {
        super(ErrorCodes.GUEST_NOT_FOUND, m);
    }
}
