package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Resources;

/**
 * Resources Validation
 */
public class ResourcesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Resources.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
