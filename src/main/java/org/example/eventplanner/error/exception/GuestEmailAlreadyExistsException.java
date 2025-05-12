package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class GuestEmailAlreadyExistsException extends EventPlannerException {
    public GuestEmailAlreadyExistsException(String m) {
        super(ErrorCodes.GUEST_EMAIL_ALREADY_EXISTS, m);
    }
}
