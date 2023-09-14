package joh.faust;

import joh.faust.exception.ExceptionOne;
import joh.faust.exception.ExceptionThree;
import joh.faust.exception.ExceptionTwo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FifteenthServiceApi {

    @GetMapping("/")
    public String getHello() {
        return "Fifteenth Service: Hello World!";
    }

    @GetMapping("/ex1")
    public String getEx1() {
        throw new ExceptionOne();
    }

    @GetMapping("/ex2")
    public String getEx2() {
        throw new ExceptionTwo();
    }

    @GetMapping("/ex3")
    public String getEx3() {
        throw new ExceptionThree();
    }

    @ExceptionHandler({ ExceptionOne.class})
    public ResponseEntity handleException() {
        return new ResponseEntity<Object>(
                "Handled by FifteenthServiceApi", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}

