package com.appvenir.vuememo.exception.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.appvenir.vuememo.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> userNotFoundHandler(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                            .message("User form validatio failed.")
                                            .path(request.getRequestURI())
                                            .data(errors)
                                            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
    }
    
}
