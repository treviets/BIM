package com.redsun.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.redsun.dao.UserDao;
import com.redsun.entities.Menu;
import com.redsun.entities.User;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Autowired
	HttpServletRequest request;

	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		User userLogin;
		try {
			userLogin = userDao.getUserByUsername(username);
			List<Menu> menus = userDao.getMenusByUsername(username);
			for(Menu menu : menus){
				String permission[] = menu.getPermission().split("|");
				for(String p : permission){
					if(!p.equals("|")){
						authorities.add(new SimpleGrantedAuthority(menu.getName()+"_"+p));
					}
					
				}
				
			}
			
			MyUserDetails userDetail = new MyUserDetails(username, userLogin.getPassword(), true, true, true, true,
					authorities);
			userDetail.setPermissions(authorities);
			userDetail.setListMenus(menus);
			userDetail.setClientId(userLogin.getClientId());
			userDetail.setRole(userLogin.getRole());
			return userDetail;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
