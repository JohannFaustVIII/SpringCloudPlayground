package joh.faust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CBService {

    @Value("${sixService.url}")
    private String gatewayUrl;

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public CBService(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreaker = circuitBreakerFactory.create("customCircuitBreaker");
        this.restTemplate = new RestTemplate();
    }

    public String getUuid() {
        String url = gatewayUrl + "/uuid";

        return circuitBreaker.run( () -> restTemplate.getForObject(url, String.class), throwable -> "none");
    }
}
