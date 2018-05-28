package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Milestones;

/**
 * Milestones Validation
 */
public class MilestonesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Milestones.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
