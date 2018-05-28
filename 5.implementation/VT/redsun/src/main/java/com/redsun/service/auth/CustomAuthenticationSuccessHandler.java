package com.redsun.service.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
  
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
                                        HttpServletResponse httpServletResponse,  
                                        Authentication authentication)  
            throws IOException, ServletException {  
        //do some logic here if you want something to be done whenever  
        //the user successfully logs in.  
        //set our response to OK status  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
  
        //since we have created our custom success handler, its up to us to where
        String contextPath = httpServletRequest.getContextPath().toString();
        String url = httpServletRequest.getRequestURI().toString();
        //we will redirect the user after successfully login
        if(!url.endsWith("j_spring_security_check")){
        	httpServletResponse.sendRedirect(contextPath+"/dashboard?redirect="+url); 
        } else {
        	httpServletResponse.sendRedirect(contextPath+"/dashboard");
        }
    }  
  
    
}  
