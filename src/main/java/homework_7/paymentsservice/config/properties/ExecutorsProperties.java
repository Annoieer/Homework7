package homework_7.paymentsservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorsProperties {
    private final RestTemplateProperties paymentsExecutorClient;

    @ConstructorBinding
    public ExecutorsProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }

    public RestTemplateProperties getPaymentsExecutorClient() {
        return paymentsExecutorClient;
    }
}
