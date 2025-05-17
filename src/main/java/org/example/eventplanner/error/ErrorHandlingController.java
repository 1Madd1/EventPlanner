package org.example.eventplanner.error;

import org.example.eventplanner.error.exception.EventPlannerException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(EventPlannerException.class)
    public ResponseEntity<ExceptionResponse> generalEventPlannerException(EventPlannerException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setCode(ex.getCode());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(Integer.parseInt(ex.getCode())));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> generalException(Exception ex) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setMessage(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.);
//    } Create other necessary exception handlers

}
