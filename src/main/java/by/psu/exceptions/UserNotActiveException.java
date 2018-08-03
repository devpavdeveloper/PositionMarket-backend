package by.psu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotActiveException extends RuntimeException {
    private int codeError = HttpStatus.BAD_REQUEST.value();
    private String reason = "This user account is deactivated";

    public UserNotActiveException() {}

    public UserNotActiveException(String message) {
        this.reason = message;
    }
}
