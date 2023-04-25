package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondServiceApi {

    @GetMapping("/")
    public String getHello() {
        return "Second Service: Hello World!";
    }
}