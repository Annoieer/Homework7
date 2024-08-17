package homework_7.paymentsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProductDto {
    private Long id;
    private String account;
    private BigDecimal balance;
    private String productType;
    private String username;
}
