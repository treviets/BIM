package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProjectResources extends AbstractEntity {
	private int resourceId;
	private double salary;
	private int quantity;
	private String resourceCode;
	private String resourceName;
	private String emailOfResponsible;
	private int projectId;
	private int clientId;
	private int baselineId;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getEmailOfResponsible() {
		return emailOfResponsible;
	}

	public void setEmailOfResponsible(String emailOfResponsible) {
		this.emailOfResponsible = emailOfResponsible;
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

	public int getBaselineId() {
		return baselineId;
	}

	public void setBaselineId(int baselineId) {
		this.baselineId = baselineId;
	}

}
