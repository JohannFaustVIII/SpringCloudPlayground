package joh.faust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstServiceApi {

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @GetMapping("/")
    public String getHello() {
        return instanceId + ": First Service: Hello World!";
    }
}
