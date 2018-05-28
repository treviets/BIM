package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Referencables;

/**
 * Referencables Validation
 */
public class ReferencablesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Referencables.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
