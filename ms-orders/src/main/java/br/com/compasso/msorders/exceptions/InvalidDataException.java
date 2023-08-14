package br.com.compasso.msorders.exceptions;

public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -1641049136523197632L;
    private ExceptionResponse exceptionResponse;

    public InvalidDataException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
