package com.redsun.doc.entities;

import com.redsun.doc.utils.RedSunConstants;

import java.util.Map;


@SuppressWarnings("rawtypes")
public class Result {
	private int status;
	private String description;
	private Map result;

	/**
	 * New instant with default property values.
	 */
	public Result() {
	}

	/**
	 * New instant with parameter values.
	 * @param status
	 * @param description
	 * @param result
	 */
	public Result(int status, String description, Map result) {
		this.status = status;
		this.description = description;
		this.result = result;
	}

	/**
	 * New instant with default status.
	 * @param result
	 * @param isOk
	 */
	public Result(Map result, boolean isOk){
		this.status = RedSunConstants.STATUS_FAILED_FLAG;
		this.description = RedSunConstants.STATUS_FAILED_STRING;
		
		if(isOk){
			this.status = RedSunConstants.STATUS_SUCCESS_FLAG;
			this.description = RedSunConstants.STATUS_SUCCESS_STRING;
		} 
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public Map getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Map result) {
		this.result = result;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
