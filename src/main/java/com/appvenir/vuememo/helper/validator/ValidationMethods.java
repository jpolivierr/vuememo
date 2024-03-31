package com.appvenir.vuememo.helper.validator;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;

public abstract class ValidationMethods {

    
    public boolean validateEmail(Object email ) {
         return EmailValidator.getInstance().isValid((String) email);         
    }

    public boolean minChar(Object text, int minimum) {
        return GenericValidator.minLength((String) text, minimum);
   }

    public boolean isNotNull(String text) {
        return text != null && !text.isEmpty();
    }



    
}
