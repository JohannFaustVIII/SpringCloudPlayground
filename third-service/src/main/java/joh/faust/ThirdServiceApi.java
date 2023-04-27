package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdServiceApi {

    @GetMapping("/")
    public String getHello() {
        return "Third Service: Hello World!";
    }
}
