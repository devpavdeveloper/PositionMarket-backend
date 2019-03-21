package by.psu.controllers;

import by.psu.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionDataHandler extends ExceptionAbstractHandler {

    private final Logger logger = Logger.getLogger(ExceptionDataHandler.class.getName());

    @ExceptionHandler
    private ResponseEntity<ExceptionAbstractHandler.AwesomeException> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        logger.severe(entityNotFoundException.getMessage());
        return new ResponseEntity<>(new AwesomeException(HttpStatus.PRECONDITION_FAILED.value(), "Entity is not founded"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionAbstractHandler.AwesomeException> handleEntityNotFoundException(Exception ex) {
        logger.severe(ex.getMessage());
        return new ResponseEntity<>(new AwesomeException(HttpStatus.PRECONDITION_FAILED.value(), "Entity is not founded"), HttpStatus.NOT_FOUND);
    }

}
