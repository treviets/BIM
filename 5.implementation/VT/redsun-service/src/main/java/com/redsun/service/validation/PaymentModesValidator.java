package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.PaymentModes;

/**
 * PaymentModes Validation
 */
public class PaymentModesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return PaymentModes.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
