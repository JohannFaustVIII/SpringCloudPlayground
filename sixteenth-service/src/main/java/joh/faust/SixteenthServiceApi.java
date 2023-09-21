package joh.faust;

import joh.faust.service.SimpleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SixteenthServiceApi {

    private final SimpleService service;

    public SixteenthServiceApi(SimpleService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getHello() {
        service.getSomething(15, "ABC");
        return "Sixteenth Service:" + service.getMessage();
    }

    @GetMapping("/exception")
    public String getException() {
        service.getSomething(7, "A", "X", "Y", "Z", "Q", "MN");
        return "Sixteenth Service:" + service.getException();
    }
}
