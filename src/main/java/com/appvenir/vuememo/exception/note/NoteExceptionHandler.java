package com.appvenir.vuememo.exception.note;

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
public class NoteExceptionHandler {

    @ExceptionHandler(value = {NoteNotFoundException.class})
    public ResponseEntity<Object> noteNotFoundExceptionExceptionHandler(NoteNotFoundException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoteTitleAlreadyExistsException.class})
    public ResponseEntity<Object> noteTitleAlreadyExistsExceptionHandler(NoteTitleAlreadyExistsException ex, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                            .message(ex.getMessage())
                                            .path(request.getRequestURI())
                                            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    
}
