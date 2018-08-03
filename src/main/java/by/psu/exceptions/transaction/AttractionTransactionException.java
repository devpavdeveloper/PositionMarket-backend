package by.psu.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AttractionTransactionException extends RuntimeException {

    public AttractionTransactionException(String reason) {
        super(reason);
    }
}
