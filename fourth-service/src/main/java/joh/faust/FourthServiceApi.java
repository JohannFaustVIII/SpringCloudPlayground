package joh.faust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class FourthServiceApi {

    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping("/")
    public String getHello() {
        return "Fourth Service: Hello World!\nExample property is: " + exampleProperty;
    }
}
