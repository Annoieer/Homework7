package homework_7.paymentsservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public ServiceNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
