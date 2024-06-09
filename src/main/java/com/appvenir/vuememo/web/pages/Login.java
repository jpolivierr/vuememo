package com.appvenir.vuememo.web.pages;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;
import com.appvenir.vuememo.web.PageTemplate.DefaultAttribute;
import com.appvenir.vuememo.web.PageTemplate.PageTemplate;

@Controller
@RequestMapping("/login")
public class Login extends PageTemplate {

    public Login(UserService userService){
        super("Login | Vuememo");
        pageAttribute.addLink(DefaultAttribute.globalLinkTagAttribute());
    }

    @GetMapping
    public String index(
        @RequestParam(required = false) boolean error,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String emailError,
        @RequestParam(required = false) String passwordError,
        Model model
        ){

        var formError = new HashMap<>();

        if(emailError != null) formError.put("email", emailError);
        
        if(passwordError != null) formError.put("password", passwordError);

        if(error){
          formError.put("authFailure", "Invalid credentials");
        }

        User user = new User();
        user.setEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("formError", formError);
        return renderPage("login", model);
    }

    
}
