package com.redsun.hrm.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Resources entity
 */
@Component
@Scope("prototype")
public class Departments extends AbstractEntity {
	private String code;
	private String name;
	private int parentId;
	private String wbs;
	private int level;
	private int manager;
	private String chiefName;
	private String img;
	private int companyId;
	private int isTrash;
	private int clientId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getWbs() {
		return wbs;
	}
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getChiefName() {
		return chiefName;
	}
	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getIsTrash() {
		return isTrash;
	}
	public void setIsTrash(int isTrash) {
		this.isTrash = isTrash;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
}
