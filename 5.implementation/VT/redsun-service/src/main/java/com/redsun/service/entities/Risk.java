package com.redsun.service.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Risk extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectId;
	private String projectName;
	private String name;
	private String description;
	private int riskTypeId;
	private String typeName;
	private String cause;
	private String impact;
	private int severityId;
	private String severityName;
	private int likelihoodId;
	private String likelihoodName;
	private int criticalityId;
	private String criticalityName;
	private Date creationDate;
	private String stringCreationDate;
	private int statusId;
	private String statusName;
	private Date planningEndDate;
	private String stringPlanningEndDate;
	private Date actualEndDate;
	private String stringActualEndDate;
	private String result;
	private Date doneDate;
	private String stringDoneDate;
	private Date handledDate;
	private String stringHandledDate;
	private int priorityId;
	private String priorityName;
	private BigDecimal impactCost;
	private int totalCount;
	private int clientId;
	private Date updateDate;
	private String stringUpdateDate;
	private String updateBy;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRiskTypeId() {
		return riskTypeId;
	}

	public void setRiskTypeId(int riskTypeId) {
		this.riskTypeId = riskTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public int getSeverityId() {
		return severityId;
	}

	public void setSeverityId(int severityId) {
		this.severityId = severityId;
	}

	public String getSeverityName() {
		return severityName;
	}

	public void setSeverityName(String severityName) {
		this.severityName = severityName;
	}

	public int getLikelihoodId() {
		return likelihoodId;
	}

	public void setLikelihoodId(int likelihoodId) {
		this.likelihoodId = likelihoodId;
	}

	public String getLikelihoodName() {
		return likelihoodName;
	}

	public void setLikelihoodName(String likelihoodName) {
		this.likelihoodName = likelihoodName;
	}

	public int getCriticalityId() {
		return criticalityId;
	}

	public void setCriticalityId(int criticalityId) {
		this.criticalityId = criticalityId;
	}

	public String getCriticalityName() {
		return criticalityName;
	}

	public void setCriticalityName(String criticalityName) {
		this.criticalityName = criticalityName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStringCreationDate() {
		return stringCreationDate;
	}

	public void setStringCreationDate(String stringCreationDate) {
		this.stringCreationDate = stringCreationDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getPlanningEndDate() {
		return planningEndDate;
	}

	public void setPlanningEndDate(Date planningEndDate) {
		this.planningEndDate = planningEndDate;
	}

	public String getStringPlanningEndDate() {
		return stringPlanningEndDate;
	}

	public void setStringPlanningEndDate(String stringPlanningEndDate) {
		this.stringPlanningEndDate = stringPlanningEndDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getStringActualEndDate() {
		return stringActualEndDate;
	}

	public void setStringActualEndDate(String stringActualEndDate) {
		this.stringActualEndDate = stringActualEndDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public String getStringDoneDate() {
		return stringDoneDate;
	}

	public void setStringDoneDate(String stringDoneDate) {
		this.stringDoneDate = stringDoneDate;
	}

	public Date getHandledDate() {
		return handledDate;
	}

	public void setHandledDate(Date handledDate) {
		this.handledDate = handledDate;
	}

	public String getStringHandledDate() {
		return stringHandledDate;
	}

	public void setStringHandledDate(String stringHandledDate) {
		this.stringHandledDate = stringHandledDate;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public BigDecimal getImpactCost() {
		return impactCost;
	}

	public void setImpactCost(BigDecimal impactCost) {
		this.impactCost = impactCost;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStringUpdateDate() {
		return stringUpdateDate;
	}

	public void setStringUpdateDate(String stringUpdateDate) {
		this.stringUpdateDate = stringUpdateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
