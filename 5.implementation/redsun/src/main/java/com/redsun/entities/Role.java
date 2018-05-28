package com.redsun.entities;

import java.util.ArrayList;
import java.util.List;

public class Role {

	private int id;

	private String name;
	private String description;
	private String content;
	private String position;
	private List<RolePermissionMenu> menus;

	public Role() {
		menus = new ArrayList<>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the menus
	 */
	public List<RolePermissionMenu> getMenus() {
		return menus;
	}

	/**
	 * @param menus
	 *            the menu to set
	 */
	public void setMenu(List<RolePermissionMenu> menus) {
		this.menus = menus;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

}
