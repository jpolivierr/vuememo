package com.appvenir.vuememo.exception.validationException;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.appvenir.vuememo.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationErrorHandler(ValidationException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                                .timestamp(LocalDateTime.now())
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .message("Validation failed.")
                                                .path(request.getRequestURI())
                                                .data(ex.getErrors())
                                                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    
}
