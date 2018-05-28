package com.redsun.service.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.redsun.entities.Menu;

public class MyUserDetails extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Menu> listMenus;
	private int clientId;
	private String avatar;
	
	private Collection<GrantedAuthority> permissions;
	
	
	
	/**
	 * @return the authorities
	 */
	public Collection<GrantedAuthority> getPermissions() {
		return permissions;
	}


	/**
	 * @param authorities the authorities to set
	 */
	public void setPermissions(Collection<GrantedAuthority> permissions) {
		this.permissions = permissions;
	}


	/**
	 * @return the listMenu
	 */
	public List<Menu> getListMenus() {
		return listMenus;
	}


	/**
	 * @param listMenu the listMenu to set
	 */
	public void setListMenus(List<Menu> listMenus) {
		this.listMenus = listMenus;
	}
	
	


	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}


	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	
	
	
	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}


	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public MyUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
}
