package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Units entity
 */
@Component
@Scope("prototype")
public class Units extends AbstractEntity {

	// name
	private String name;
	// devices
	private int devices;
	// client_id
	private int clientId;
	// totalCount
	private int totalCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDevices() {
		return devices;
	}

	public void setDevices(int devices) {
		this.devices = devices;
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
