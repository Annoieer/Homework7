package homework_7.paymentscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-core.properties")
public class ApplicationPaymentCore {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationPaymentCore.class, args);
    }
}
