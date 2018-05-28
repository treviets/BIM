package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.CronExecutions;

/**
 * CronExecutions Validation
 */
public class CronExecutionsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return CronExecutions.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
