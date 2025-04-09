package org.example.eventplanner.core.error.exception;

import lombok.Getter;

@Getter
public class EventPlannerException extends RuntimeException {
    private final String code;
    private final String message;

    public EventPlannerException(String c, String m) {
        super(m);
        this.code = c;
        this.message = m;
    }
}
