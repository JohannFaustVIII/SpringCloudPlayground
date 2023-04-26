package joh.faust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondServiceApi {

    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping("/")
    public String getHello() {
        return "Second Service: Hello World!\nExample property is: " + exampleProperty;
    }
}