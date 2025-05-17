package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class NoDataException extends EventPlannerException {
    public NoDataException(String m) {
        super(ErrorCodes.ENTITY_NOT_FOUND, m);
    }
}
