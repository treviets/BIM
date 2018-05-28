package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.OverallProgresses;

/**
 * OverallProgresses Validation
 */
public class OverallProgressesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return OverallProgresses.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
