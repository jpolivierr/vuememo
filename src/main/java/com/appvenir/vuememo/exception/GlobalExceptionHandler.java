package com.appvenir.vuememo.exception;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNoteFountException(EntityNotFoundException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.NOT_ACCEPTABLE.value())
                                            .error(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                                            .error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
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
