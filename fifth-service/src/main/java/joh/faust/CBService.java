package joh.faust;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CBService {

    @Value("${sixService.name}")
    private String serviceName;

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final EurekaClient eurekaClient;

    public CBService(CircuitBreakerFactory circuitBreakerFactory, EurekaClient eurekaClient) {
        this.circuitBreaker = circuitBreakerFactory.create("customCircuitBreaker");
        this.eurekaClient = eurekaClient;
        this.restTemplate = new RestTemplate();
    }

    public String getUuid() {
        InstanceInfo instanceInfo = eurekaClient.getApplication(serviceName).getInstances().get(0);
        String url = instanceInfo.getHomePageUrl() + "/uuid";

        return circuitBreaker.run( () -> restTemplate.getForObject(url, String.class), throwable -> "none");
    }
}
