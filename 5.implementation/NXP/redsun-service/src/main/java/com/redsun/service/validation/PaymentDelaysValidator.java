package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.PaymentDelays;

/**
 * PaymentDelays Validation
 */
public class PaymentDelaysValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return PaymentDelays.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
