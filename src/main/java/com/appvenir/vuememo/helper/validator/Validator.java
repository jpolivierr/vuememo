package com.appvenir.vuememo.helper.validator;

import java.util.HashMap;
import java.util.List;

import com.appvenir.vuememo.exception.validationException.ValidationException;


public abstract class Validator {

    private HashMap<String,String> errors = new HashMap<>();

    public abstract List<Rule> rules();

    public void validate(){

        for(Rule rule : rules()){
            if(!rule.getErrors().isEmpty()){
                errors.put(rule.getField(), rule.getErrors().get(0));
            }
        }
     
        if(errors.size() > 0) throw new ValidationException(errors);
    }


}
