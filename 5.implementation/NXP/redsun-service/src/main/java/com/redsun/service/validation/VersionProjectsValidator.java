package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.VersionProjects;

/**
 * VersionProjects Validation
 */
public class VersionProjectsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return VersionProjects.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
