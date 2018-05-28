package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.CallForTenders;

/**
 * CallForTenders Validation
 */
public class CallForTendersValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return CallForTenders.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
