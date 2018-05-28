package com.redsun.service.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskResourceTracking extends AbstractEntity {
	private int taskId;
	private int projectId;
	private int actualTime;
	private int overTime;
	private double coefficient;
	private Date dateLog;
	private String stringDateLog;
	private String owner;
	private int resourceId;
	private int quantity;
	private double salary;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getActualTime() {
		return actualTime;
	}

	public void setActualTime(int actualTime) {
		this.actualTime = actualTime;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public Date getDateLog() {
		return dateLog;
	}

	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
	}

	public String getStringDateLog() {
		return stringDateLog;
	}

	public void setStringDateLog(String stringDateLog) {
		this.stringDateLog = stringDateLog;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
