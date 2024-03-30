package com.appvenir.vuememo.exception.validationException;

import java.util.HashMap;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final HashMap<String,String> errors;
    
    public ValidationException(HashMap<String,String> errors){
        this.errors = errors;
    }

    public ValidationException(String message, HashMap<String,String> errors){
        super(message);
        this.errors = errors;
    }



}
