package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.BillLines;

/**
 * BillLines Validation
 */
public class BillLinesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return BillLines.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
