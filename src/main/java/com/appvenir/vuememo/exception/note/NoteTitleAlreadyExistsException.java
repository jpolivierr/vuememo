package com.appvenir.vuememo.exception.note;

public class NoteTitleAlreadyExistsException extends RuntimeException{

    public NoteTitleAlreadyExistsException(String message) {
        super(message);
    }
    
}
