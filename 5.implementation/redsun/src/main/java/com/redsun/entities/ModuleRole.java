package com.redsun.entities;

public class ModuleRole {
	private int id;
	private String username;
	private ModulePermission modulePermission;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the modulePermission
	 */
	public ModulePermission getModulePermission() {
		return modulePermission;
	}
	/**
	 * @param modulePermission the modulePermission to set
	 */
	public void setModulePermission(ModulePermission modulePermission) {
		this.modulePermission = modulePermission;
	}
	
}
