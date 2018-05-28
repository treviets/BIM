package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Statuses entity
 */
@Component
@Scope("prototype")
public class Statuses extends AbstractEntity {

	// name
	private String name;
	// scope
	private String scope;
	// color
	private String color;
	// sort_order
	private int sortOrder;
	// client_id
	private int clientId;
	// totalCount
	private Integer totalCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
