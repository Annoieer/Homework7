package homework_7.paymentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-service.properties")
public class ApplicationPaymentService {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationPaymentService.class, args);
    }
}
