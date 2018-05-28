package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskResources extends AbstractEntity {
	private int projectId;
	private int taskId;
	private int resourceId;
	private double salary;
	private int quantity;
	private int actualWork;
	private String warning;
	private String resourceCode;
	private String resourceName;
	private String emailOfAssignee;
	private int clientId;
	private int baselineId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

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
	
	

	/**
	 * @return the actualWork
	 */
	public int getActualWork() {
		return actualWork;
	}

	/**
	 * @param actualWork the actualWork to set
	 */
	public void setActualWork(int actualWork) {
		this.actualWork = actualWork;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
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

	public String getEmailOfAssignee() {
		return emailOfAssignee;
	}

	public void setEmailOfAssignee(String emailOfAssignee) {
		this.emailOfAssignee = emailOfAssignee;
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
