package joh.faust;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CBService {

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public CBService(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreaker = circuitBreakerFactory.create("recomended");
        this.restTemplate = new RestTemplate();
    }

    public String getUuid() {
        String url = "http://localhost:8081/sixth/uuid";

        return circuitBreaker.run( () -> restTemplate.getForObject(url, String.class), throwable -> "none");
    }
}
