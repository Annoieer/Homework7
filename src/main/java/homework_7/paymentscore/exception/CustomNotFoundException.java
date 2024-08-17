package homework_7.paymentscore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public CustomNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}