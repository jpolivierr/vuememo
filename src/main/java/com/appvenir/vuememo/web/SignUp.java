package com.appvenir.vuememo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvenir.vuememo.domain.users.User;
import com.appvenir.vuememo.domain.users.UserService;

@Controller
@RequestMapping("/sign-up")
public class SignUp {

    private final UserService userService;

    public SignUp(UserService userService){

        this.userService = userService;

    }

    @PostMapping
    public String saveUserForm(@ModelAttribute User user){
        userService.saveUser(user);
        return "index";
    }
    
}
