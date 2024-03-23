package com.appvenir.vuememo.exception;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                                            .error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
