package joh.faust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstServiceApi {

    private int calls = 0;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @GetMapping("/")
    public String getHello() {
        return instanceId + ": First Service: Hello World! Number of calls: " + (++calls);
    }
}
