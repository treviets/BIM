package com.redsun.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Chatting{
	private double id;
	
	private int clientId;
	private int projectId;
	private String createdBy;
	private String message;
	private String createdDate;
	private int scopes;
	private String avatar;
	private String groupId;
	
	
	/**
	 * @return the id
	 */
	public double getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(double id) {
		this.id = id;
	}
	/**
	 * @return the client_id
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param client_id the client_id to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the project_id
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param project_id the project_id to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the scopes
	 */
	public int getScopes() {
		return scopes;
	}
	/**
	 * @param scopes the scopes to set
	 */
	public void setScopes(int scopes) {
		this.scopes = scopes;
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
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	
}
