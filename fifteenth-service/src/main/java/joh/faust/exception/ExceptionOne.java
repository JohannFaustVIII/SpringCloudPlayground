package joh.faust.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Handled by Response Status", value = HttpStatus.UNAUTHORIZED)
public class ExceptionOne extends RuntimeException {
}
