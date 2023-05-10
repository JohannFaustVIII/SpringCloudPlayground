package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FifthServiceApi {

    private final CBService cbService;

    public FifthServiceApi(CBService cbService) {
        this.cbService = cbService;
    }

    @GetMapping("/")
    public String getHello() {
        return "Fifth Service: UUID received from 6th service: " + cbService.getUuid();
    }
}
