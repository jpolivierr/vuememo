package com.appvenir.vuememo.domain.users;

import com.appvenir.vuememo.helper.validator.Rule;
import com.appvenir.vuememo.helper.validator.Validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserValidator extends Validator {

    private final User user;
   
    public void rules(){

        Rule firstNameRule = new Rule("firstName", user.getFirstName())
                                .notNull("First name is required");

        Rule lastNameRule = new Rule("lastName", user.getLastName())
                                .notNull("Last name is required");

        Rule emailRule = new Rule("email", user.getEmail())
                                .isValidEmail("Email is not valid");

        Rule passwordRule = new Rule("password", user.getPassword())
                                 .notNull("Password is required");


        addRule(firstNameRule, lastNameRule, emailRule, passwordRule);                

    }

    
}
