package com.redsun.service.validation;

import java.util.LinkedHashMap;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Quotations;

/**
 * Quotations Validation
 */
public class QuotationsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Quotations.class.equals(aClass) || LinkedHashMap.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
