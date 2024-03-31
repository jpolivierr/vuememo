package com.appvenir.vuememo.helper.validator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Getter
public class Rule extends ValidationMethods {

        private final String field;
        private final String value;
        public List<String> errors;

        public Rule(String field, String value){
            this.field = field;
            this.value = value;
            this.errors = new ArrayList<>();
        }

        public Rule isValidEmail(String message) {
            var expresion = super.validateEmail(value);
            isValid(expresion, message);
            return this;
        }

        public Rule minChar(int minimum, String message) {
            var expresion = super.minChar(value, minimum);
            isValid(expresion, message);
            return this;
        }

        public Rule notNull(String message) {
            var expresion = super.isNotNull(value);
            isValid(expresion, message);
            return this;
        }

        private void isValid(Boolean expresion, String message){
            if(!expresion){
                errors.add(message);
            }
        }


}
