package com.appvenir.vuememo.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found");
    }
    
}
