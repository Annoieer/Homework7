package homework_7.paymentscore.controller;

import homework_7.paymentscore.dto.ProductTypeDto;
import homework_7.paymentscore.dto.ProductTypeResponseDto;
import homework_7.paymentscore.service.ProductTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping(value = "/all")
    public ProductTypeResponseDto getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping(value = "/{typeId}")
    public ProductTypeDto getByUserId(@PathVariable Long typeId) {
        return productTypeService.getProductType(typeId);
    }
}
