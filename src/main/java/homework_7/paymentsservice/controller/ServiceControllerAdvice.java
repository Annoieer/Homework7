package homework_7.paymentsservice.controller;

import homework_7.paymentscore.dto.ErrorResponseDto;
import homework_7.paymentsservice.exception.IntegrationException;
import homework_7.paymentsservice.exception.ServiceNotFoundException;
import homework_7.paymentsservice.exception.ServicePaymentRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ServiceControllerAdvice {

    @ExceptionHandler(ServiceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleUserNotFoundException(ServiceNotFoundException exception) {
        return new ErrorResponseDto(exception.getMessage(), exception.getHttpStatus().value());
    }

    @ExceptionHandler(ServicePaymentRequiredException.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ErrorResponseDto handlePaymentRequiredException(ServicePaymentRequiredException exception) {
        return new ErrorResponseDto(exception.getMessage(), exception.getHttpStatus().value());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleIntegrationException(IntegrationException exception) {
        return new ErrorResponseDto(exception.getMessage(), exception.getHttpStatus().value());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentTypeMismatchException() {
        return new ErrorResponseDto("Неверный тип параметра!", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponseDto handleResourceAccessException() {
        return new ErrorResponseDto("Основной сервис недоступен", HttpStatus.SERVICE_UNAVAILABLE.value());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMissingServletRequestParameterException() {
        return new ErrorResponseDto("Не передан параметр запроса", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponseDto handleHttpRequestMethodNotSupportedException() {
        return new ErrorResponseDto("Метод не поддерживается", HttpStatus.METHOD_NOT_ALLOWED.value());
    }
}
