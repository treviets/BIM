package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Types;

/**
 * Types Validation
 */
public class TypesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Types.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
