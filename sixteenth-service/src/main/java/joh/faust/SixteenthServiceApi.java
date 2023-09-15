package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SixteenthServiceApi {

    @GetMapping("/")
    public String getHello() {
        return "Sixteenth Service: Hello World!";
    }
}
