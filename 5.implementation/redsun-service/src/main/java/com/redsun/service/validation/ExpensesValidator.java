package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Expenses;

/**
 * Expenses Validation
 */
public class ExpensesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Expenses.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
