package com.redsun.service.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Project extends AbstractEntity {

	private String name;
	private String location;
	private String description;
	private int customerId;
	private String customerName;
	private String code;
	private int parentId;
	private Date startDate;
	private String stringStartDate;
	private Date endDate;
	private int duration;
	private int workSunday;
	private String stringEndDate;
	private Date closedDate;
	private String StringClosedDate;
	private Date doneDate;
	private String stringDoneDate;
	private int typeId;
	private String typeName;
	private String howManyDayPaymentDelay;
	private int statusId;
	private String statusName;
	private Date creationDate;
	private String stringCreationDate;
	private Date lastUpdateDateTime;
	private String stringLastUpdateDateTime;
	private int totalCount;
	private String updateBy;
	private BigDecimal amount;
	private int clientId;
	private int projectType;

	public String getName() {
		return name;
	}

	/**
	 * @return the projectType
	 */
	public int getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType
	 *            the projectType to set
	 */
	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStringStartDate() {
		return stringStartDate;
	}

	public void setStringStartDate(String stringStartDate) {
		this.stringStartDate = stringStartDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getWorkSunday() {
		return workSunday;
	}

	public void setWorkSunday(int workSunday) {
		this.workSunday = workSunday;
	}

	public String getStringEndDate() {
		return stringEndDate;
	}

	public void setStringEndDate(String stringEndDate) {
		this.stringEndDate = stringEndDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getStringClosedDate() {
		return StringClosedDate;
	}

	public void setStringClosedDate(String stringClosedDate) {
		StringClosedDate = stringClosedDate;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getHowManyDayPaymentDelay() {
		return howManyDayPaymentDelay;
	}

	public void setHowManyDayPaymentDelay(String howManyDayPaymentDelay) {
		this.howManyDayPaymentDelay = howManyDayPaymentDelay;
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

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getStringLastUpdateDateTime() {
		return stringLastUpdateDateTime;
	}

	public void setStringLastUpdateDateTime(String stringLastUpdateDateTime) {
		this.stringLastUpdateDateTime = stringLastUpdateDateTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
