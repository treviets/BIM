package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Trends;

/**
 * Trends Validation
 */
public class TrendsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Trends.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
