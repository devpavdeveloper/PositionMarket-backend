package by.psu.security.exceptions;

public class UserExistsException extends RuntimeException {
    private String reason = null;
    private Integer code  = null;

    public UserExistsException(String reason, Integer code) {
        this.reason = reason;
        this.code = code;
    }
}
