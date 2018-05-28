package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Statuses;

/**
 * Statuses Validation
 */
public class StatusesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Statuses.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
