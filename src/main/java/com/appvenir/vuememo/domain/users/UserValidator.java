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
public class UserValidator{

    // private final UserLogin userLogin;
    private final Validator validator;

    public UserValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public <T> void validate(T userRegistration) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(userRegistration);
        checkErrors(violations);
    }

    public <T> void checkErrors(Set<ConstraintViolation<T>> violations){
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
    
}
