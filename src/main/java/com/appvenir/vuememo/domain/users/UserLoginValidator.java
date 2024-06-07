package com.appvenir.vuememo.domain.users;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.appvenir.vuememo.exception.validationException.ValidationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;

@Getter
public class UserLoginValidator{

    // private final UserLogin userLogin;
    private final Validator validator;

    public UserLoginValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

    }

    public void validate(UserLogin userLogin) throws ValidationException {
        Set<ConstraintViolation<UserLogin>> violations = validator.validate(userLogin);
        if (!violations.isEmpty()) {
            Map<String, String> errors = violations.stream()
                .collect(Collectors.toMap(
                    violation -> violation.getPropertyPath().toString(),
                    ConstraintViolation::getMessage,
                    (existing, replacement) -> existing
                ));
            throw new ValidationException(errors);
        }
    }

    // public List<Rule> rules() {

    //     return List.of(
    //         new Rule("email", userLogin.getEmail())
    //                 .isValidEmail("Email is not valid"),
    //         new Rule("password", userLogin.getPassword())
    //                       .notNull("Password is required")                    
    //     );               
                          
    // }


    
}
