package com.appvenir.vuememo.helper.validator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Getter
public class Rule {

        private final String field;
        private final Object value;
        public List<String> errors;

        private Rule(Builder builder){
            this.field = builder.getField();
            this.value = builder.getValue();
            this.errors = builder.getErrors();
        }

        @Getter
        public static class Builder extends ValidationMethods{

                private String field;
                private Object value;
                public List<String> errors = new ArrayList<>();

                public Builder set(String field, Object value){
                    this.field = field;
                    this.value = value;
                    return this;
                }

                public Builder  isValidEmail() {
                    var expresion = super.validateEmail(value);
                    isValid(expresion, "Email is not valid");
                    return this;
                }

                public Builder minChar(int minimum) {
                    var expresion = super.minChar(value, minimum);
                    isValid(expresion, "Must be 8 character long");
                    return this;
                }

                private void isValid(Boolean expresion, String value){
                    if(!expresion){
                        errors.add(value);
                    }
                }

                public Rule build(){
                    return new Rule(this);
                }

        } 


}
