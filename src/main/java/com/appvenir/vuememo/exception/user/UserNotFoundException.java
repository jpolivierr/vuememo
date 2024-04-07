package com.appvenir.vuememo.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found");
        System.out.println("##############################");
        System.out.println("Exception called...");
    }
    
}
