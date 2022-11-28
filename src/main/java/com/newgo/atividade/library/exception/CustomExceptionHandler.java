package com.newgo.atividade.library.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error("Empty result data access", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InvalidAuthorException.class})
    protected  ResponseEntity<Object> handleInvalidAuthorException(InvalidAuthorException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error("Invalid author", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
