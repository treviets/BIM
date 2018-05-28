package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Teams;

/**
 * Teams Validation
 */
public class TeamsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Teams.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
