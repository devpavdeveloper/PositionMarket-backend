package by.psu.controllers;

import by.psu.exceptions.authorization.UserBlockedException;
import by.psu.exceptions.authorization.UserIncorrectException;
import by.psu.exceptions.authorization.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAbstractHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    private ResponseEntity<AwesomeException> handleUserNotFoundException() {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.NOT_FOUND.value(), "The user is not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserBlockedException.class})
    private ResponseEntity<AwesomeException> handleUserBlockedException() {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.PRECONDITION_FAILED.value(), "The user is temporarily blocked"), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({UserIncorrectException.class})
    private ResponseEntity<AwesomeException> handleUserIncorrectException() {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST.value(), "Incorrect username or password"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    protected static class AwesomeException {
        private int code;
        private String message;
    }

}
