package joh.faust;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Observed(
        name = "tenthController"
)
@RestController
public class TenthServiceApi {

    private static final Logger logger = LoggerFactory.getLogger(TenthServiceApi.class);

    @Observed(
            name = "hello.get",
            contextualName = "calling hello endpoint",
            lowCardinalityKeyValues = {"class.name", "TenthServiceApi"}
    )
    @GetMapping("/")
    public String getHello() {
        logger.info("Called Hello endpoint.");
        return "Tenth Service: Hello World!";
    }

    @Observed(
            name = "goodbye.get",
            contextualName = "calling goodbye endpoint",
            lowCardinalityKeyValues = {"class.name", "TenthServiceApi"}
    )
    @GetMapping("/bye")
    public String getGoodbye() {
        logger.info("Called Goodbye endpoint.");
        return "Tenth Service: Goodbye!";
    }
}
