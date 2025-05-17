package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class EntityNotFoundException extends EventPlannerException {
    public EntityNotFoundException(String m) {
        super(ErrorCodes.ENTITY_NOT_FOUND, m);
    }
}
