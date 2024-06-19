package com.appvenir.vuememo.domain.users;

import com.appvenir.vuememo.domain.users.model.User;

public class DemoUser {

    public static User get(){
        User user = new User();
        user.setName("Frederic");
        user.setEmail("js@gmail.com");
        return user;
    }
}
