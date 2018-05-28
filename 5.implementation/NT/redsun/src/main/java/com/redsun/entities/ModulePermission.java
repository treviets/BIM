package com.redsun.entities;

public class ModulePermission {
	private int id;
	private String name;
	private String key;
	private ModuleProperty moduleProperty;
	private String permission;

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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	/**
	 * @return the moduleProperty
	 */
	public ModuleProperty getModuleProperty() {
		return moduleProperty;
	}
	/**
	 * @param moduleProperty the moduleProperty to set
	 */
	public void setModuleProperty(ModuleProperty moduleProperty) {
		this.moduleProperty = moduleProperty;
	}
	
}
