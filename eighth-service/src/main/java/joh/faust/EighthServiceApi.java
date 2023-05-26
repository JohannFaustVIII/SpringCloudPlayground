package joh.faust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EighthServiceApi {

    private static final Logger logger = LoggerFactory.getLogger(EighthServiceApi.class);
    private final List<Integer> integers = List.of(8, 808, 18, -8);

    @GetMapping("/")
    public String getHello() {
        logger.info("Called Hello endpoint.");
        return "Eighth Service: Hello World! Integers are: " + integers;
    }

    @GetMapping("/integers")
    public List<Integer> getIntegers() {
        logger.info("Called integers endpoint.");
        return integers;
    }
}
