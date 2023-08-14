package br.com.compasso.mscustomers.exceptions;

public class DuplicatedCpfException extends RuntimeException {

    private final static long serialVersionUID = -5784765254741478438L;
    private ExceptionResponse exceptionResponse;
    public DuplicatedCpfException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
