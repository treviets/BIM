package com.redsun.bpmn.entities;

/* GeneralInformation entity
 * Represent general information each of phase of a process
 */

public class GeneralInformation extends AbstractEntity {
	
	private Integer id;
	
	private String name;
	
	private String uploadPerson;
	
	private String uploadDate;
	
	private String updatePerson;
	
	private String updateDate;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}
	
	public String getUploadPerson() {
		return this.uploadPerson;
	}
	
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getUploadDate() {
		return this.uploadDate;
	}
	
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public String getUpdatePerson() {
		return this.updatePerson;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateDate() {
		return this.updateDate;
	}
	
	
}

