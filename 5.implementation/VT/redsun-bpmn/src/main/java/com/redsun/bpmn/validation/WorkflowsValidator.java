package com.redsun.bpmn.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.redsun.bpmn.entities.Workflows;

/**
 * Workflows Validation
 */
public class WorkflowsValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Workflows.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
