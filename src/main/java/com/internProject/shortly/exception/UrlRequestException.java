package com.internProject.shortly.exception;


import org.springframework.http.HttpStatus;

// error handler for normal request errpr
public class UrlRequestException extends RuntimeException  {
    private HttpStatus httpStatus;
    public UrlRequestException(HttpStatus httpStatus ,String message) {
        super(message);
        this.httpStatus =httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
