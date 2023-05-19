package joh.faust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EighthServiceApi {

    private final List<Integer> integers = List.of(8, 808, 18, -8);

    @GetMapping("/")
    public String getHello() {
        return "Eighth Service: Hello World! Integers are: " + integers;
    }

    @GetMapping("/integers")
    public List<Integer> getIntegers() {
        return integers;
    }
}
