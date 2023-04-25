package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstServiceApi {

    @GetMapping("/")
    public String getHello() {
        return "First Service: Hello World!";
    }
}
