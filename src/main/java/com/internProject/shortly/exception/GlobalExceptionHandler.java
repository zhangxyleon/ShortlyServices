package com.internProject.shortly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

    // error handler for normal request error
    @ExceptionHandler({UrlRequestException.class})
    public ResponseEntity<Object> handleUrlException(UrlRequestException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
