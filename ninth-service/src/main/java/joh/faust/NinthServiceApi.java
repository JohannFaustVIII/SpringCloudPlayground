package joh.faust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NinthServiceApi {

    private static final Logger logger = LoggerFactory.getLogger(NinthServiceApi.class);
    private final EighthApiClient eighthApiClient;

    public NinthServiceApi(EighthApiClient eighthApiClient) {
        this.eighthApiClient = eighthApiClient;
    }

    @GetMapping("/")
    public String getHello() {
        logger.info("Calling hello endpoint, and using eight api to get values.");
        return "Ninth Service: Hello World! Values from eighth service: " + eighthApiClient.getIntegers();
    }
}
