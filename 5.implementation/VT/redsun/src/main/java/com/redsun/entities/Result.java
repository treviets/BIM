package com.redsun.entities;

import java.util.Map;


public class Result {

	private int status;
	private String description;
	private Map<Object, Object> result;

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(final Map<Object, Object> result) {
		this.result = result;
	}
}
