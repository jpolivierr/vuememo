package com.appvenir.vuememo.exception.user;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException{

    public InvalidCredentialsException(String msg) {
        super(msg);
        //TODO Auto-generated constructor stub
    }
    
}
