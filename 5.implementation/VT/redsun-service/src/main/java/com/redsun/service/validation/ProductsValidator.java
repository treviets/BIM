package com.redsun.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.service.entities.Products;

/**
 * Products Validation
 */
public class ProductsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Products.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
