package by.psu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
    }
    private int codeError = HttpStatus.BAD_REQUEST.value();

    public DuplicateUserException(String message) {
        super(message);
    }
}
