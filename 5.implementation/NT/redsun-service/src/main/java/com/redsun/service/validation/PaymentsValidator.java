package com.redsun.service.validation;

import java.util.LinkedHashMap;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Payments;

/**
 * Payments Validation
 */
public class PaymentsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Payments.class.equals(aClass) || LinkedHashMap.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
