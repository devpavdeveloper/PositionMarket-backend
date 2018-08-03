package by.psu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenExpiredException extends RuntimeException {
    private int codeError = HttpStatus.BAD_REQUEST.value();
    private static String reason = "Токен истен, запросите другой";

    public TokenExpiredException() {super(reason);}

    public TokenExpiredException(String message) {
        super(message);
    }
}
