package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Documents;

/**
 * Documents Validation
 */
public class DocumentsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Documents.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
