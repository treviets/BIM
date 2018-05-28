package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.ActivityPrices;

/**
 * ActivityPrices Validation
 */
public class ActivityPricesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return ActivityPrices.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
