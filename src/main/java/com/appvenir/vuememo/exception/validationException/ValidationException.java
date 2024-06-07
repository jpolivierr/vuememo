package com.appvenir.vuememo.exception.validationException;

import java.util.Map;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final Map<String,String> errors;
    
    public ValidationException(Map<String,String> errors){
        this.errors = errors;
        System.out.println(this.errors);
    }

    public ValidationException(String message, Map<String,String> errors){
        super(message);
        this.errors = errors;
    }



}
