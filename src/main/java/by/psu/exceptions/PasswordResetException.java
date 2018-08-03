package by.psu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordResetException extends RuntimeException{
    private int codeError = HttpStatus.BAD_REQUEST.value();
    private static String reason = "Password cannot only be changed every 24 hours";

    public PasswordResetException() {
        super(reason);
    }
}
