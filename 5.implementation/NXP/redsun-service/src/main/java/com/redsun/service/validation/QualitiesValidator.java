package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Qualities;

/**
 * Qualities Validation
 */
public class QualitiesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Qualities.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
