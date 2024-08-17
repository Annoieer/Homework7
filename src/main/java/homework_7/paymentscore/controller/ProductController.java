package homework_7.paymentscore.controller;

import homework_7.paymentscore.dto.ProductDto;
import homework_7.paymentscore.dto.ProductResponseDto;
import homework_7.paymentscore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public ProductResponseDto getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{productId}")
    public ProductDto getByProductId(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping(value = "/byUser/{userId}")
    public ProductResponseDto getByUserId(@PathVariable Long userId) {
        ArrayList<ProductDto> products = productService.getProductsByUserId(userId);
        BigDecimal sum = new BigDecimal(0);
        for (ProductDto product : products) {
            sum = sum.add(product.getBalance());
        }
        return new ProductResponseDto(sum, products);
    }

    @GetMapping(value = "/byUser/{userId}/{id}")
    public ProductDto getByIdAndUserId(@PathVariable Long userId, @PathVariable Long id) {
        return productService.getProductByIdAndUserId(userId, id);
    }

    @PostMapping(value = "/byUser/{userId}/{id}")
    public ProductDto executePayment(@PathVariable Long userId, @PathVariable Long id, @RequestParam Long sum) {
        return productService.executePayment(userId, id, sum);
    }
}
