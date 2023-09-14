package joh.faust;

import joh.faust.exception.ExceptionOne;
import joh.faust.exception.ExceptionTwo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ExceptionOne.class, ExceptionTwo.class})
    protected ResponseEntity handleException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Handled by Global Exception Handler", new HttpHeaders(), HttpStatus.PAYMENT_REQUIRED);
    }
}
