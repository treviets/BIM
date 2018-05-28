package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Types entity
 */
@Component
@Scope("prototype")
public class Types extends AbstractEntity {

	// scope
	private String scope;
	// name
	private String name;
	// sort_order
	private int sortOrder;
	// color
	private String color;
	// id_workflow
	private int idWorkflow;
	// description
	private String description;
	// id_planning_mode
	private int idPlanningMode;
	// id_category
	private int idCategory;
	// lock_no_left_on_done
	// client_id
	private int clientId;
	// totalCount
	private int totalCount;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(int idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdPlanningMode() {
		return idPlanningMode;
	}

	public void setIdPlanningMode(int idPlanningMode) {
		this.idPlanningMode = idPlanningMode;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
