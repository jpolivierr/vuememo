package com.appvenir.vuememo.domain.users;

import java.util.List;

import com.appvenir.vuememo.helper.validator.Rule;
import com.appvenir.vuememo.helper.validator.Validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginValidator extends Validator{

    private final UserLogin userLogin;

    @Override
    public List<Rule>  rules() {

        return List.of(
            new Rule("email", userLogin.getEmail())
                    .isValidEmail("Email is not valid"),
            new Rule("password", userLogin.getPassword())
                          .notNull("Password is required")                    
        );               
                          
    }


    
}
