package joh.faust.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SimpleService {

    public String getMessage() {
        return "Hello World!";
    }

    public String getException() {
        throw new RuntimeException();
    }

    public String getSomething(int i, String... s) {
        return Arrays.toString(s) + " " + i;
    }
}
