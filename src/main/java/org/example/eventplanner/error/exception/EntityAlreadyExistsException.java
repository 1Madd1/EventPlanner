package org.example.eventplanner.error.exception;

import org.example.eventplanner.error.ErrorCodes;

public class EntityAlreadyExistsException extends EventPlannerException {
    public EntityAlreadyExistsException(String m) {
        super(ErrorCodes.ENTITY_ALREADY_EXISTS, m);
    }
}
