package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Recipients;

/**
 * Recipients Validation
 */
public class RecipientsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Recipients.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
