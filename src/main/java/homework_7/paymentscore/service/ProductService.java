package homework_7.paymentscore.service;

import homework_7.paymentscore.dto.ProductDto;
import homework_7.paymentscore.dto.ProductResponseDto;
import homework_7.paymentscore.exception.CustomNotFoundException;
import homework_7.paymentscore.exception.CustomPaymentRequiredException;
import homework_7.paymentscore.mapper.ProductMapper;
import homework_7.paymentscore.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public ProductDto getProduct(Long id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Продукт с id = " + id + " не существует", HttpStatus.NOT_FOUND)));
    }

    public ArrayList<ProductDto> getProductsByUserId(Long id) {
        try {
            ArrayList<ProductDto> products = new ArrayList<>();
            productRepository.getProductsByUserId(id).forEach(user -> products.add(productMapper.toDto(user)));
            if (products.size() == 0) {
                throw new CustomNotFoundException("Пользователь с id = " + id + " не существует либо у него нет продуктов", HttpStatus.NOT_FOUND);
            }
            return products;
        } catch (NullPointerException exception) {
            throw new CustomNotFoundException("Пользователь с id = " + id + " не существует либо у него нет продуктов", HttpStatus.NOT_FOUND);
        }
    }

    public ProductDto getProductByIdAndUserId(Long userId, Long id) {
        try {
            return productMapper.toDto(productRepository.getProductsByIdAndUserId(id, userId));
        } catch (NullPointerException exception) {
            throw new CustomNotFoundException("Пользователь с id = " + userId + " не существует либо у него нет продукта с id = " + id,
                    HttpStatus.NOT_FOUND);
        }
    }

    public ProductDto executePayment(Long userId, Long id, Long sum) {
        try {
            ProductDto productDto = getProductByIdAndUserId(userId, id);
            BigDecimal balance = productDto.getBalance();
            balance = balance.subtract(BigDecimal.valueOf(sum));

            if (balance.signum() == -1) {
                throw new CustomPaymentRequiredException("Недостаточно средств на счёте", HttpStatus.PAYMENT_REQUIRED);
            }

            productRepository.setProductBalance(id, userId, balance);

            productDto.setBalance(balance);
            return productDto;
        } catch (NullPointerException exception) {
            throw new CustomNotFoundException("Пользователь с id = " + userId + " не существует либо у него нет продукта с id = " + id,
                    HttpStatus.NOT_FOUND);
        }
    }

    public ProductResponseDto getAllProducts() {
        BigDecimal sum = new BigDecimal(0);
        List<ProductDto> products = new ArrayList<>();
        productRepository.findAll().forEach(user -> products.add(productMapper.toDto(user)));
        return new ProductResponseDto(sum, products);
    }
}
