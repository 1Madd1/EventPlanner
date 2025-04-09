package org.example.eventplanner.core.error.exception;

import org.example.eventplanner.core.error.ErrorCodes;

public class GuestEmailAlreadyExistsException extends EventPlannerException {
    public GuestEmailAlreadyExistsException(String m) {
        super(ErrorCodes.GUEST_EMAIL_ALREADY_EXISTS, m);
    }
}
