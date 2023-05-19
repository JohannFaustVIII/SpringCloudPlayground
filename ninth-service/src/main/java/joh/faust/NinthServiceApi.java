package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NinthServiceApi {

    private final EighthApiClient eighthApiClient;

    public NinthServiceApi(EighthApiClient eighthApiClient) {
        this.eighthApiClient = eighthApiClient;
    }

    @GetMapping("/")
    public String getHello() {
        return "Ninth Service: Hello World! Values from eighth service: " + eighthApiClient.getIntegers();
    }
}
