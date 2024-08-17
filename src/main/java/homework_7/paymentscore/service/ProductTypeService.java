package homework_7.paymentscore.service;

import homework_7.paymentscore.dto.ProductTypeDto;
import homework_7.paymentscore.dto.ProductTypeResponseDto;
import homework_7.paymentscore.exception.CustomNotFoundException;
import homework_7.paymentscore.mapper.ProductTypeMapper;
import homework_7.paymentscore.repository.ProductTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    public ProductTypeDto getProductType(Long id) {
        return productTypeMapper.toDto(productTypeRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Тип продукта с id = " + id + " не существует", HttpStatus.NOT_FOUND)));
    }

    public ProductTypeResponseDto getAllProductTypes() {
        List<ProductTypeDto> types = new ArrayList<>();
        productTypeRepository.findAll().forEach(user -> types.add(productTypeMapper.toDto(user)));
        return new ProductTypeResponseDto(types.size(), types);
    }
}
