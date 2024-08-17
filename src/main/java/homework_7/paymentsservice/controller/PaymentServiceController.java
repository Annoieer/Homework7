package homework_7.paymentsservice.controller;

import homework_7.paymentsservice.dto.ServiceProductDto;
import homework_7.paymentsservice.dto.ServiceProductResponseDto;
import homework_7.paymentsservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/service")
public class PaymentServiceController {

    private final PaymentService paymentService;

    public PaymentServiceController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/getProducts/{userId}")
    public ServiceProductResponseDto getProductsFromClient(@PathVariable Long userId) {
        return paymentService.getAllClientProducts(userId);
    }

    @GetMapping(value = "/getProducts/{userId}/{id}")
    public ServiceProductDto getProductByIdFromClient(@PathVariable Long userId, @PathVariable Long id) {
        return paymentService.getClientProduct(userId, id);
    }

    @PostMapping(value = "/getProducts/{userId}/{id}")
    public ServiceProductDto executePayment(@PathVariable Long userId, @PathVariable Long id, @RequestParam Long sum) {
        return paymentService.executePayment(userId, id, sum);
    }
}
