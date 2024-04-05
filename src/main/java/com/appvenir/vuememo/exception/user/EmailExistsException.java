package com.appvenir.vuememo.exception.user;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(){
        super("Email already exists");
    }
    
}
