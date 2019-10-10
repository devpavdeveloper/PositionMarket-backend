package by.psu.controllers.exc_handler;

import by.psu.exceptions.ImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionResourcesHandler {

    private final Logger logger = Logger.getLogger(ExceptionDataHandler.class.getName());

    @ExceptionHandler
    private ResponseEntity<ExceptionAbstractHandler.AwesomeException> handleImageNotFoundException(ImageNotFoundException imageNotFoundException) {
        logger.severe(imageNotFoundException.getMessage());
        return new ResponseEntity<>(new ExceptionAbstractHandler.AwesomeException(HttpStatus.PRECONDITION_FAILED, imageNotFoundException.getMessage()), HttpStatus.PRECONDITION_FAILED);
    }

}
