package com.redsun.service.controller;

import com.redsun.service.entities.Result;
import com.redsun.service.exceptions.CustomBadRequestException;
import com.redsun.service.exceptions.CustomSQLException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HauLL on 1/19/2017.
 * This class is used for handle all exception of system
 */
@RestControllerAdvice
public class GlobalExceptionController {

	private Logger log = Logger.getLogger(GlobalExceptionController.class); 
	
    /**
     * method to handle bad request exception for all system
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(CustomBadRequestException.class)
    public Result handleBadRequestException(HttpServletRequest request, CustomBadRequestException exception){
        final HttpStatus status = exception.getCode() == null ? HttpStatus.BAD_REQUEST : exception.getCode();
        final String exceptionInfo = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(exception);
        
        // log.
        log.error(exceptionInfo);
        // return.
        final Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", new ResponseEntity<Object>(exceptionInfo, status));
        return new Result(result, false);
    }

    /**
     * method to handle all exception which does not have handler
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleGeneralException(HttpServletRequest request, Exception exception){
        final String exceptionInfo = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(exception);
        
        // log.
        log.error(exceptionInfo);
        // return.
        final Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", new ResponseEntity<Object>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR));
        return new Result(result, false);
    }

    /**
     * method to handle all sql exception for all system
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(CustomSQLException.class)
    public Result handleCustomSQLException(HttpServletRequest request, CustomSQLException exception){
        final HttpStatus status = exception.getCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR : exception.getCode();
        final String exceptionInfo = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(exception);
        
       // log.
        log.error(exceptionInfo);
        // return.
        final Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", new ResponseEntity<Object>(exceptionInfo, status));
        return new Result(result, false);
    }

    /**
     * Method to handle validation exception.
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationError(MethodArgumentNotValidException exception){
        final BindingResult bindingResult = exception.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final String exceptionInfo = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(exception);
        
        // log.
        log.error(exceptionInfo);
        // return.
        final Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", fieldErrors);
        return new Result(result, false);
    }
}
