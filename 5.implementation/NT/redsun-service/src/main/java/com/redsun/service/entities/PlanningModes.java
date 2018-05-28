package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * PlanningModes entity
 */
@Component
@Scope("prototype")
public class PlanningModes extends AbstractEntity {

    // name
    private String name;
    // code
    private String code;
    // sort_order
    private Integer sortOrder;
    // mandatory_start_date
	private Integer mandatoryStartDate;
    // mandatory_end_date
	private Integer mandatoryEndDate;
    // apply_to
    private String applyTo;
    // idle
	private Integer idle;
    // mandatory_duration
	private Integer mandatoryDuration;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// name
    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

	// code
    public void setCode(final String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }

	// sortOrder
    public void setSortOrder(final Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getSortOrder() {
        return this.sortOrder;
    }

	// mandatoryStartDate
    public void setMandatoryStartDate(final Integer mandatoryStartDate) {
        this.mandatoryStartDate = mandatoryStartDate;
    }
    public Integer getMandatoryStartDate() {
        return this.mandatoryStartDate;
    }

	// mandatoryEndDate
    public void setMandatoryEndDate(final Integer mandatoryEndDate) {
        this.mandatoryEndDate = mandatoryEndDate;
    }
    public Integer getMandatoryEndDate() {
        return this.mandatoryEndDate;
    }

	// applyTo
    public void setApplyTo(final String applyTo) {
        this.applyTo = applyTo;
    }
    public String getApplyTo() {
        return this.applyTo;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// mandatoryDuration
    public void setMandatoryDuration(final Integer mandatoryDuration) {
        this.mandatoryDuration = mandatoryDuration;
    }
    public Integer getMandatoryDuration() {
        return this.mandatoryDuration;
    }

	// clientId
    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }
    public Integer getClientId() {
        return this.clientId;
    }

	// totalCount
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }

	// toString
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(code);
        sb.append("|");
        sb.append(sortOrder);
        sb.append("|");
        sb.append(mandatoryStartDate);
        sb.append("|");
        sb.append(mandatoryEndDate);
        sb.append("|");
        sb.append(applyTo);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(mandatoryDuration);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
