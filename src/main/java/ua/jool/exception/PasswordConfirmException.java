package ua.jool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PasswordConfirmException extends RuntimeException {

    public PasswordConfirmException(String message) {
        super(message);
    }

    public PasswordConfirmException(String message, Throwable cause) {
        super(message, cause);
    }
}
