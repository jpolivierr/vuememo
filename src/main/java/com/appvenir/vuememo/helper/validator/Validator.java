package com.appvenir.vuememo.helper.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.appvenir.vuememo.exception.validationException.ValidationException;


public abstract class Validator {

    private HashMap<String,String> errors = new HashMap<>();
    private List<Rule> rules = new ArrayList<>();

    public abstract void rules();

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void addRule(List<Rule> rules){
        this.rules = rules;
    }

    public void addRule(Rule ...ruleArray){
        for(Rule rule : ruleArray){
            this.rules.add(rule);
        }
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
