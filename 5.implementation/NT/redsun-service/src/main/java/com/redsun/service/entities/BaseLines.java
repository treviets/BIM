package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Clients entity
 */
@Component
@Scope("prototype")
public class BaseLines extends AbstractEntity {

	private String name;
	private int baselineType;
	private String description;
	private Date baselineDate;
	private int projectId;
	private int clientId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaselineType() {
		return baselineType;
	}

	public void setBaselineType(int baselineType) {
		this.baselineType = baselineType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBaselineDate() {
		return baselineDate;
	}

	public void setBaselineDate(Date baselineDate) {
		this.baselineDate = baselineDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
