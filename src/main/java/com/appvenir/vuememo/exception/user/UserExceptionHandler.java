package com.appvenir.vuememo.exception.user;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
}
