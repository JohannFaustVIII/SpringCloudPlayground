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
        return "Sixteenth Service:" + service.getMessage();
    }

    @GetMapping("/exception")
    public String getException() {
        return "Sixteenth Service:" + service.getException();
    }
}
