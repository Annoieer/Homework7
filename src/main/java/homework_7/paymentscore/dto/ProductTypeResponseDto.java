package homework_7.paymentscore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductTypeResponseDto {
    private Integer totalCount;
    List<ProductTypeDto> productTypes;
}
