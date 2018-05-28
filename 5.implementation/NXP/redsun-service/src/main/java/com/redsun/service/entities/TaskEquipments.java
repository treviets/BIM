package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskEquipments extends AbstractEntity {
	private int projectId;
	private int taskId;
	private String owner;
	private int equipmentId;
	private String equipmentName;
	private String unit;
	private String description;
	private int quantity;
	private int actualWork;
	private int actualTime;
	private double netPrice;
	private double price;
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
	
	

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	
	
	/**
	 * @return the actualTime
	 */
	public int getActualTime() {
		return actualTime;
	}

	/**
	 * @param actualTime the actualTime to set
	 */
	public void setActualTime(int actualTime) {
		this.actualTime = actualTime;
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
