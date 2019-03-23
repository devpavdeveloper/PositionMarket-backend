package by.psu.controllers.exc_handler;

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

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionAbstractHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = Logger.getLogger(ExceptionAbstractHandler.class.getName());


    @ExceptionHandler
    private ResponseEntity<AwesomeException> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        logger.severe(userNotFoundException.getMessage());
        return new ResponseEntity<>(new AwesomeException(HttpStatus.NOT_FOUND.value(), "The user is not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<AwesomeException> handleUserBlockedException(UserBlockedException userBlockedException) {
        logger.severe(userBlockedException.getMessage());
        return new ResponseEntity<>(new AwesomeException(HttpStatus.PRECONDITION_FAILED.value(), "The user is temporarily blocked"), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({UserIncorrectException.class})
    private ResponseEntity<AwesomeException> handleUserIncorrectException(UserIncorrectException userIncorrectException) {
        logger.severe(userIncorrectException.getMessage());
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST.value(), "Incorrect username or password"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    static class AwesomeException {
        private int code;
        private String message;
    }

    @Data
    @AllArgsConstructor
    static class MessageException {
        private int code;
        private String message;
        private String reason;
    }

}
