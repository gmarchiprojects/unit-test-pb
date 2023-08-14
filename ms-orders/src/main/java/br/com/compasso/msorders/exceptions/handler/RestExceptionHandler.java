package br.com.compasso.msorders.exceptions.handler;


import br.com.compasso.msorders.enums.ErrorCodes;
import br.com.compasso.msorders.exceptions.CustomerNotFoundException;
import br.com.compasso.msorders.exceptions.DuplicatedCpfException;
import br.com.compasso.msorders.exceptions.ExceptionResponse;
import br.com.compasso.msorders.exceptions.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("RestExceptionHandler.handleMethodArgumentNotValid - Method Argument Not Valid - error: [{}]", ex.getMessage(), ex);
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(f -> errors.add(String.format("%s : %s", f.getField(), f.getDefaultMessage())));
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.VALIDATION_FAILED, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("RestExceptionHandler.handleHttpMessageNotReadable - Message Not Readable Exception - error: [{}]", ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.MALFORMED_JSON, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
        log.error("RestExceptionHandler.handleCustomerNotFound - Customer Not Found - error: [{}]", ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleInvalidData(InvalidDataException ex) {
        log.error("RestExceptionHandler.handleInvalidData - Invalid data - error: [{}]", ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.INVALID_DATA, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(DuplicatedCpfException.class)
    public final ResponseEntity<Object> handleDuplicatedCpf(DuplicatedCpfException ex) {
        log.error("RestExceptionHandler.handleDuplicatedCpf - Duplicated CPF - error: [{}]", ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DUPLICATED_CPF, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
