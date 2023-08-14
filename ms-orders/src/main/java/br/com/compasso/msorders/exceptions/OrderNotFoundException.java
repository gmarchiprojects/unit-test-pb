package br.com.compasso.msorders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -7673488275591493173L;
    private ExceptionResponse exceptionResponse;

    public OrderNotFoundException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
