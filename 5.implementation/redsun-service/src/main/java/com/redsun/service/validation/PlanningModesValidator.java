package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.PlanningModes;

/**
 * PlanningModes Validation
 */
public class PlanningModesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return PlanningModes.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
