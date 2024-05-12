package com.appvenir.vuememo.domain.users;

import java.util.List;

import com.appvenir.vuememo.helper.validator.Rule;
import com.appvenir.vuememo.helper.validator.Validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserValidator extends Validator {

    private final User user;
   
    public List<Rule> rules(){

        Rule name = new Rule("name", user.getName())
                                .notNull("Full name is required");

        Rule emailRule = new Rule("email", user.getEmail())
                                .isValidEmail("Email is not valid");

        Rule passwordRule = new Rule("password", user.getPassword())
                                 .notNull("Password is required")
                                 .minChar(8, "Password must contain 8 characters minimum");

        return List.of(name, emailRule, passwordRule);                

    }

    
}
