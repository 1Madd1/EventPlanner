package org.example.eventplanner.error;

public class ErrorCodes {
    public static final String GUEST_EMAIL_ALREADY_EXISTS = "e001";
    public static final String EVENT_SCHEDULED_DATE_ALREADY_EXISTS = "e002";
    public static final String EVENT_NOT_FOUND = "e003";
    public static final String GUEST_NOT_FOUND = "404";
    public static final String EVENT_AND_GUEST_MUST_EXIST = "e005";
    public static final String INVALID_EVENT_SCHEDULED_TIME = "e006";
    public static final String EVENT_GUEST_NOT_FOUND = "e007";
    //Treba srediti kodove i dovrsiti centralni error handler
}
