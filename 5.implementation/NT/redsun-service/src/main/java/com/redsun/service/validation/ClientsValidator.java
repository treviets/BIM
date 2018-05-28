package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Clients;

/**
 * Clients Validation
 */
public class ClientsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Clients.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
