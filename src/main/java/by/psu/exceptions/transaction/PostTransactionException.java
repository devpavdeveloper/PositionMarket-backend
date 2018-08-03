package by.psu.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostTransactionException extends RuntimeException {
    public PostTransactionException(String reason) {
        super(reason);
    }
}
