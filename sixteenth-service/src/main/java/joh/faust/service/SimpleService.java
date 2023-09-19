package joh.faust.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    public String getMessage() {
        return "Hello World!";
    }

    public String getException() {
        throw new RuntimeException();
    }
}
