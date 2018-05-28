package com.redsun.entities;

/**
 * @author My Laptop
 *
 */
public class PermissionProperty {
	private int permissionId;
	private String name;
	private String description;
	/**
	 * @return the permissionId
	 */
	public int getPermissionId() {
		return permissionId;
	}
	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
