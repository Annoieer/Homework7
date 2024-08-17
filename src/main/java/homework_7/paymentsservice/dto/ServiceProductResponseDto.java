package homework_7.paymentsservice.dto;

import homework_7.paymentscore.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProductResponseDto {
    private BigDecimal totalSum;
    List<ProductDto> products;
}
