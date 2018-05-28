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
public class DirectoryResource extends AbstractEntity {
	
	private int directoryId;
	private int projectId;
	private int resourceId;
	private int clientId;
	private String userName;
	/**
	 * @return the directoryId
	 */
	public int getDirectoryId() {
		return directoryId;
	}
	/**
	 * @param directoryId the directoryId to set
	 */
	public void setDirectoryId(int directoryId) {
		this.directoryId = directoryId;
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
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	
}

