package com.redsun.service.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlanningElement extends AbstractEntity {
	  private int projectId;
	  private int taskId;
	  private String taskName;
	  private String wbs;
	  private String parent_id;
	  private Date startDate;
	  private Date endDate;
	  private int estimateTime;
	  private int estimateQuantity;
	  private int actuallyTime;
	  private int completed;
	  private int actuallyQuantity;
	  private int planningCost;
	  private int actuallyCost;
	  private String code;
	  private String resourceName;
	  private String unitName;
	  private int totalActuallyTime;
	  private int totalPlanningCost;
	  private int totalActuallyCost;
	  private double BCWP; 
	  private double SV;
	  private double CV;
	  private double CPI;
	  private double SPI;
	  private int monthly;
	  private int yearly;
	  private int salary;
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the wbs
	 */
	public String getWbs() {
		return wbs;
	}
	/**
	 * @param wbs the wbs to set
	 */
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}
	/**
	 * @return the parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the estimateTime
	 */
	public int getEstimateTime() {
		return estimateTime;
	}
	/**
	 * @param estimateTime the estimateTime to set
	 */
	public void setEstimateTime(int estimateTime) {
		this.estimateTime = estimateTime;
	}
	/**
	 * @return the estimateQuantity
	 */
	public int getEstimateQuantity() {
		return estimateQuantity;
	}
	/**
	 * @param estimateQuantity the estimateQuantity to set
	 */
	public void setEstimateQuantity(int estimateQuantity) {
		this.estimateQuantity = estimateQuantity;
	}
	/**
	 * @return the actuallyTime
	 */
	public int getActuallyTime() {
		return actuallyTime;
	}
	/**
	 * @param actuallyTime the actuallyTime to set
	 */
	public void setActuallyTime(int actuallyTime) {
		this.actuallyTime = actuallyTime;
	}
	/**
	 * @return the completed
	 */
	public int getCompleted() {
		return completed;
	}
	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	/**
	 * @return the actuallyQuantity
	 */
	public int getActuallyQuantity() {
		return actuallyQuantity;
	}
	/**
	 * @param actuallyQuantity the actuallyQuantity to set
	 */
	public void setActuallyQuantity(int actuallyQuantity) {
		this.actuallyQuantity = actuallyQuantity;
	}
	/**
	 * @return the planningCost
	 */
	public int getPlanningCost() {
		return planningCost;
	}
	/**
	 * @param planningCost the planningCost to set
	 */
	public void setPlanningCost(int planningCost) {
		this.planningCost = planningCost;
	}
	/**
	 * @return the actuallyCost
	 */
	public int getActuallyCost() {
		return actuallyCost;
	}
	/**
	 * @param actuallyCost the actuallyCost to set
	 */
	public void setActuallyCost(int actuallyCost) {
		this.actuallyCost = actuallyCost;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the totalActuallyTime
	 */
	public int getTotalActuallyTime() {
		return totalActuallyTime;
	}
	/**
	 * @param totalActuallyTime the totalActuallyTime to set
	 */
	public void setTotalActuallyTime(int totalActuallyTime) {
		this.totalActuallyTime = totalActuallyTime;
	}
	/**
	 * @return the totalPlanningCost
	 */
	public int getTotalPlanningCost() {
		return totalPlanningCost;
	}
	/**
	 * @param totalPlanningCost the totalPlanningCost to set
	 */
	public void setTotalPlanningCost(int totalPlanningCost) {
		this.totalPlanningCost = totalPlanningCost;
	}
	/**
	 * @return the totalActuallyCost
	 */
	public int getTotalActuallyCost() {
		return totalActuallyCost;
	}
	/**
	 * @param totalActuallyCost the totalActuallyCost to set
	 */
	public void setTotalActuallyCost(int totalActuallyCost) {
		this.totalActuallyCost = totalActuallyCost;
	}
	/**
	 * @return the bCWP
	 */
	public double getBCWP() {
		return BCWP;
	}
	/**
	 * @param bCWP the bCWP to set
	 */
	public void setBCWP(double bCWP) {
		BCWP = bCWP;
	}
	/**
	 * @return the sV
	 */
	public double getSV() {
		return SV;
	}
	/**
	 * @param sV the sV to set
	 */
	public void setSV(double sV) {
		SV = sV;
	}
	/**
	 * @return the cV
	 */
	public double getCV() {
		return CV;
	}
	/**
	 * @param cV the cV to set
	 */
	public void setCV(double cV) {
		CV = cV;
	}
	/**
	 * @return the cPI
	 */
	public double getCPI() {
		return CPI;
	}
	/**
	 * @param cPI the cPI to set
	 */
	public void setCPI(double cPI) {
		CPI = cPI;
	}
	/**
	 * @return the sPI
	 */
	public double getSPI() {
		return SPI;
	}
	/**
	 * @param sPI the sPI to set
	 */
	public void setSPI(double sPI) {
		SPI = sPI;
	}
	/**
	 * @return the monthly
	 */
	public int getMonthly() {
		return monthly;
	}
	/**
	 * @param monthly the monthly to set
	 */
	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}
	/**
	 * @return the yearly
	 */
	public int getYearly() {
		return yearly;
	}
	/**
	 * @param yearly the yearly to set
	 */
	public void setYearly(int yearly) {
		this.yearly = yearly;
	}
	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}