package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.ExpenseDetails;

/**
 * ExpenseDetails Validation
 */
public class ExpenseDetailsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return ExpenseDetails.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
