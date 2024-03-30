package com.appvenir.vuememo.helper.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;

import com.appvenir.vuememo.exception.validationException.ValidationException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public abstract class Validator {

    private HashMap<String,String> errors = new HashMap<>();
    private Set<Rule> rules = new HashSet<>();

    public abstract void rules();

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void addRule(Set<Rule> rules){
        this.rules = rules;
    }

    public void validate(){
        rules();

        for(Rule rule : rules){
            if(!rule.getErrors().isEmpty()){
                errors.put(rule.getField(), rule.getErrors().get(0));
            }
        }
     
        if(errors.size() > 0) throw new ValidationException(errors);
    }


}
