package by.psu.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BadRequestException extends RuntimeException {

    private String reason;

    public BadRequestException(String message) {
        this.reason = message;
    }

    public BadRequestException(String message, Throwable cause) {
        super(cause);
        this.reason = message;
    }

}
