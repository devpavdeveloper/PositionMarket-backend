package by.psu.controllers;

import by.psu.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionDataHandler extends ExceptionAbstractHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    private ResponseEntity<ExceptionAbstractHandler.AwesomeException> handleEntityNotFoundException() {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.PRECONDITION_FAILED.value(), "Entity is not founded"), HttpStatus.NOT_FOUND);
    }

}
