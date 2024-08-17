package homework_7.paymentsservice.config;

import homework_7.paymentsservice.exception.IntegrationException;
import homework_7.paymentsservice.exception.ServiceNotFoundException;
import homework_7.paymentsservice.exception.ServicePaymentRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        switch (response.getStatusCode().value()) {
            case 400 -> throw new IntegrationException("Неверный тип параметра", HttpStatus.BAD_REQUEST);
            case 402 -> throw new ServicePaymentRequiredException("Недостаточно средств на счёте", HttpStatus.PAYMENT_REQUIRED);
            case 404 -> throw new ServiceNotFoundException("Продукт или клиент не найден", HttpStatus.NOT_FOUND);
        }
    }
}
