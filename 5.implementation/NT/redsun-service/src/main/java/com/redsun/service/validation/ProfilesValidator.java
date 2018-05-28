package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Profiles;

/**
 * Profiles Validation
 */
public class ProfilesValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Profiles.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
