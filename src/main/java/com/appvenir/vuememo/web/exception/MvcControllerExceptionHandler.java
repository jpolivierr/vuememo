package com.appvenir.vuememo.web.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.appvenir.vuememo.exception.user.UserNotFoundException;


@ControllerAdvice(annotations = Controller.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MvcControllerExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public String handleNotFoundException(UserNotFoundException ex){
        return "redirect:/login?error=true";
    }

    @ExceptionHandler(value = {NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoResourceFoundException ex){
        return "404";
    }

    // @ExceptionHandler(value = {Exception.class})
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handleException(Exception ex){
    //     return "500";
    // }

    
    
    
}
