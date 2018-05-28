package com.redsun.service.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.redsun.service.utils.RedSunConstants;

@Aspect
@Component
public class ControllerWrapper {

	//@Pointcut("within(@org.springframework.stereotype.Controller *)")
	@Pointcut("within(com.redsun.service.controller.MilestoneController) || within(com.redsun.service.controller.MilestoneTypeController)")
	public void anyControllerPointcut() {}
	
	@Pointcut("execution(* *(..))")
	public void anyMethodPointcut() {}
	
	@Around(value="anyControllerPointcut() && anyMethodPointcut()")
	public Object aroundExecution(ProceedingJoinPoint jp) {
    	// Wrapper response.
		ResponseWrapper result = new ResponseWrapper();
	    try{
	    	System.out.println("aroundExecution");
	    	// Proceed method.
	    	Object response = jp.proceed();
	    	
			result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
			result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
			result.setResult(response);
	    } catch (Throwable exception) {
	    	System.out.println("Error: " + exception.getMessage());
	    	//throw new Exception("Error", exception);
			result.setStatus(RedSunConstants.STATUS_FAILED_FLAG);
			result.setDescription(RedSunConstants.STATUS_FAILED_STRING);
			result.setResult(exception);
	    }
		return result;
	}

	/*
	@After(value="anyControllerPointcut() && anyMethodPointcut()")
    public void afterExecution(JoinPoint joinPoint) 
    {
        System.out.println("ControllerWrapper.afterExecution : " + joinPoint.getSignature().getName());
    }
	
	@AfterReturning(value="anyControllerPointcut() && anyMethodPointcut()", returning="response")
	public Object wrapResponse(Object response) {
		System.out.println("wrapResponse");
		
		return response;
	}
	*/
	
	@AfterThrowing(value="anyControllerPointcut() && anyMethodPointcut()", throwing="exception")
	public void wrapException(Throwable exception) {
		System.out.println("wrapException");
	}
}
