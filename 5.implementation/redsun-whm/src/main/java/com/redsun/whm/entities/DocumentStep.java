package com.redsun.whm.entities;

/* Document Step
 * Represent for document each of step
 */

public class DocumentStep extends AbstractEntity {
	
	private int idProject;
	private String idStep;
	private String documentName;
	private String documentDescription;
	
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	public int getIdProject() {
		return this.idProject;
	}
	public void setIdStep(String idStep) {
		this.idStep = idStep;
	}
	public String getIdStep() {
		return this.idStep;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentName() {
		return this.documentName;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	public String getDocumentDescription() {
		return this.documentDescription;
	}
}

