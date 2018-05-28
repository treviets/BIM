package com.redsun.service.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskMaterialTracking extends AbstractEntity {
	private int taskId;
	private int projectId;
	private double actualMaterial; //actual quantity
	private Date dateLog;
	private String stringDateLog;
	private String owner;
	private int materialId;
	private double netPrice;
	private double price;
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
	public double getActualMaterial() {
		return actualMaterial;
	}
	public void setActualMaterial(double actualMaterial) {
		this.actualMaterial = actualMaterial;
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
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
