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
        String firstName = user.getFirstName();

        Rule emailRule = new Rule.Builder()
                        .set("Email", user.getEmail())
                        .isValidEmail()
                        .minChar(10)
                        .build();

        addRule(emailRule);                

        // isValidEmail(email, "Email");
        // minChar(firstName, "firstName", 8);
    }

    
}
