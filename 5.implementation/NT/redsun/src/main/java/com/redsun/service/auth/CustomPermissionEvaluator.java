package com.redsun.service.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomPermissionEvaluator{
	
	public boolean hasPermission(String tagetType, String permission) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return hasPrivilege(auth, tagetType, permission);
	}

	private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
	    for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
	        if (grantedAuth.getAuthority().startsWith(targetType)) {
	        	String[] permissions = permission.split("|");
	        	for(String p : permissions){
		            if (grantedAuth.getAuthority().contains(p)) {
		                return true;
		            }
	        	}
	        }
	    }
	    return false;
	}
}
