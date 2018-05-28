package com.redsun.bpmn.entities;

/* Phase entity
 * Represent each of phase of any flows in Design process
 */

public class PhaseDocument extends AbstractEntity {
	
	private int idProject;
	private String activeStep;
	private String uploadBy;
	private String uploadDate;
	private String updateBy;
	private String updateDate;
	private String documentName;
	private String documentUrl;

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	
	public int getIdProject() {
		return this.idProject;
	}
	
	public void setActiveStep(String activeStep) {
		this.activeStep = activeStep;
	}
	
	public String getActiveStep() {
		return this.activeStep;
	}
	
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	
	public String getUploadBy() {
		return this.uploadBy;
	}
	
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getUploadDate() {
		return this.uploadDate;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public String getUpdateBy() {
		return this.updateBy;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateDate() {
		return this.updateDate;
	}
	
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getDocumentName() {
		return this.documentName;
	}
	
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	
	public String getDocumentUrl() {
		return this.documentUrl;
	}
}

