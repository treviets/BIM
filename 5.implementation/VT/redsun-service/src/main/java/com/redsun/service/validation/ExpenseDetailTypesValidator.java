package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.ExpenseDetailTypes;

/**
 * ExpenseDetailTypes Validation
 */
public class ExpenseDetailTypesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return ExpenseDetailTypes.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
