package com.petproject.minivns.exceptions;

import com.petproject.minivns.json.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, status.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    ResponseEntity<Object> handleAuthenticationException(BadCredentialsException exception, HttpStatus status){
//        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, status.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
//    }
}
