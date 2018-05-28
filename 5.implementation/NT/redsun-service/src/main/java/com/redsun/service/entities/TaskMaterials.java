package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskMaterials extends AbstractEntity {
	private int projectId;
	private int taskId;
	private int projectMaterialId;
	private double netPrice;
	private double price;
	private String materialName;
	private String description;
	private String unit;
	private String code;
	private double quantity;
	private double actualQuantity;
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

	public int getProjectMaterialId() {
		return projectMaterialId;
	}

	public void setProjectMaterialId(int projectMaterialId) {
		this.projectMaterialId = projectMaterialId;
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

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(double actualQuantity) {
		this.actualQuantity = actualQuantity;
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