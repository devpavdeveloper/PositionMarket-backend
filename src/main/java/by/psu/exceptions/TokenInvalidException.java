package by.psu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenInvalidException extends RuntimeException {
    private int codeError = HttpStatus.BAD_REQUEST.value();
    private String reason = "This token is invalid";

    public TokenInvalidException() {}

    public TokenInvalidException(String message) {
        this.reason = message;
    }
}
