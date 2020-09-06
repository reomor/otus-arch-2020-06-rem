package ru.otus.softwaredesign.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.otus.softwaredesign.domain.ErrorResponse;
import ru.otus.softwaredesign.exception.BaseDockerAppException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseDockerAppException.class})
    protected ResponseEntity<ErrorResponse> handle(BaseDockerAppException e, WebRequest request) {
        return new ResponseEntity<>(
            new ErrorResponse(e.getMessage() != null ? e.getMessage() : "Unknown error"),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> handle(Exception e, WebRequest request) {
        return new ResponseEntity<>(
            new ErrorResponse(e.getMessage() != null ? e.getMessage() : "Unknown error"),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
