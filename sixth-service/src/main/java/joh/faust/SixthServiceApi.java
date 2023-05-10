package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SixthServiceApi {

    private String uuid = UUID.randomUUID().toString();

    @GetMapping("/")
    public String getHello() {
        return "Sixth Service: Random UUID: " + uuid;
    }

    @GetMapping("/uuid")
    public String getUuid() {
        return uuid;
    }
}