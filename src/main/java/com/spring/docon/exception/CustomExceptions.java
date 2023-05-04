package com.spring.docon.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptions {

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail onException(RuntimeException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
        return pd;
    }
}


