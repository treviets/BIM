package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Providers;

/**
 * Providers Validation
 */
public class ProvidersValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Providers.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
