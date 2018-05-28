package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Tenders;

/**
 * Tenders Validation
 */
public class TendersValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Tenders.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
