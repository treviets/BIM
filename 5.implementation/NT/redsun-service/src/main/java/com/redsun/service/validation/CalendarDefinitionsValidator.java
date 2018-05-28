package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.CalendarDefinitions;

/**
 * CalendarDefinitions Validation
 */
public class CalendarDefinitionsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return CalendarDefinitions.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
