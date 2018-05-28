package com.redsun.doc.entities;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/* GeneralInformation entity
 * Represent general information each of phase of a process
 */
@Component
@Scope("prototype")
public class Directory extends AbstractEntity {
	
	private String name;
	private String location;
	private int parentId;
	private int projectId;
	private int clientId;
	private Date createDate;
	private Date updateDate;
	private String createUserName;
	private int trash;
	private boolean collapsed = true;
	private List<Directory> subFolders;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * @return the trash
	 */
	public int getTrash() {
		return trash;
	}
	/**
	 * @param trash the trash to set
	 */
	public void setTrash(int trash) {
		this.trash = trash;
	}
	/**
	 * @return the collapsed
	 */
	public boolean isCollapsed() {
		return collapsed;
	}
	/**
	 * @param collapsed the collapsed to set
	 */
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	/**
	 * @return the subFolders
	 */
	public List<Directory> getSubFolders() {
		return subFolders;
	}
	/**
	 * @param subFolders the subFolders to set
	 */
	public void setSubFolders(List<Directory> subFolders) {
		this.subFolders = subFolders;
	}
	
}

