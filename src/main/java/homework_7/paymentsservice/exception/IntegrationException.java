package homework_7.paymentsservice.exception;

import org.springframework.http.HttpStatus;

public class IntegrationException extends RuntimeException {

    private final HttpStatus httpStatus;

    public IntegrationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
