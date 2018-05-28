package com.redsun.service.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Organization extends AbstractEntity {

	private String name;
	private String description;
	private int idle;
	private int userId;
	private int resourceId;
	private String sortOrder;
	private int organizationTypeId;
	private int organizationId;
	private Date creationDate;
	private String stringCreationDate;
	private Date lastUpdateDateTime;
	private String stringLastUpdateDateTime;
	private int clientId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdle() {
		return idle;
	}

	public void setIdle(int idle) {
		this.idle = idle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(int organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStringCreationDate() {
		return stringCreationDate;
	}

	public void setStringCreationDate(String stringCreationDate) {
		this.stringCreationDate = stringCreationDate;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	
	public String getStringLastUpdateDateTime() {
		return stringLastUpdateDateTime;
	}

	public void setStringLastUpdateDateTime(String stringLastUpdateDateTime) {
		this.stringLastUpdateDateTime = stringLastUpdateDateTime;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
}
