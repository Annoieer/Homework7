package homework_7.paymentsservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServicePaymentRequiredException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public ServicePaymentRequiredException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
