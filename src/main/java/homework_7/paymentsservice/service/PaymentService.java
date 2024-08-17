package homework_7.paymentsservice.service;

import homework_7.paymentsservice.dto.ServiceProductDto;
import homework_7.paymentsservice.dto.ServiceProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final RestTemplate executorRestClient;

    public PaymentService(RestTemplate executorRestClient) {
        this.executorRestClient = executorRestClient;
    }

    public ServiceProductResponseDto getAllClientProducts(Long clientId) {
        return executorRestClient.getForObject(
                "/products/byUser/" + clientId, ServiceProductResponseDto.class
        );
    }

    public ServiceProductDto getClientProduct(Long clientId, Long productId) {
        return executorRestClient.getForObject(
                "/products/byUser/" + clientId + "/" + productId, ServiceProductDto.class
        );
    }

    public ServiceProductDto executePayment(Long clientId, Long productId, Long sum) {
        return executorRestClient.postForObject(
                "/products/byUser/" + clientId + "/" + productId + "?sum=" + sum, null, ServiceProductDto.class
        );
    }
}
