package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Healths;

/**
 * Healths Validation
 */
public class HealthsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Healths.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
