package com.redsun.utils;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.redsun.service.ClientService;
import com.redsun.service.auth.MyUserDetails;

@Service
public class UserUtil {

	public static final int ENABLED_USER = 1;
	public static final int DISABLED_USER = 0;
	private static final String DEFAULT_AVATAR = "/redsun/static/images/default-avatar.jpg";
	@Autowired
	ClientService clientService;
	
	public int getClientIdOfLoginedUser() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){ 
        	MyUserDetails myUserDetail = (MyUserDetails)auth.getPrincipal();
        	return myUserDetail.getClientId();
        }
		return 0;
	}
	
	public String getLoginedUsername() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){ 
        	return auth.getName();
        }
		return null;
	}
	
	public String getLoginedAvatar() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetail= null;
        if (auth != null){ 
        	userDetail = (MyUserDetails)auth.getPrincipal();
        	if(userDetail.getAvatar() == null){
        		return DEFAULT_AVATAR;
        	}
        	return userDetail.getAvatar();
        }
		return null;
	}
	
	public String getLoginedRole() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetail= null;
        if (auth != null){ 
        	userDetail = (MyUserDetails)auth.getPrincipal();
        	
        	return userDetail.getRole();
        }
		return null;
	}
	
	public MyUserDetails getLoginedUser() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetail= null;
        if (auth != null){ 
        	userDetail = (MyUserDetails)auth.getPrincipal();
        }
		return userDetail;
	}
	
	public Collection<GrantedAuthority> getCurrentUserPermission(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetail= null;
        if (auth != null){ 
        	userDetail = (MyUserDetails)auth.getPrincipal();
        }
        return userDetail.getAuthorities();
	}
	
}
