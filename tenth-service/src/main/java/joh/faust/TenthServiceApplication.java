package joh.faust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TenthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenthServiceApplication.class, args);
    }

}

