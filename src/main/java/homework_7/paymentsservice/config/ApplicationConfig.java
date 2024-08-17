package homework_7.paymentsservice.config;

import homework_7.paymentsservice.config.properties.ExecutorsProperties;
import homework_7.paymentsservice.config.properties.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({
        ExecutorsProperties.class,
})
public class ApplicationConfig {
    @Bean
    public RestTemplate executorRestClient(
            ExecutorsProperties executorsProperties,
            RestTemplateResponseErrorHandler errorHandler
    ) {
        RestTemplateProperties executorClient = executorsProperties.getPaymentsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(executorClient.getUrl())
                .setConnectTimeout(executorClient.getConnectTimeout())
                .setReadTimeout(executorClient.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
