package com.redsun.service.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Task extends AbstractEntity {
	private int affectationId;
	private String taskName;
	private String wbs;
	private String description;
	private int responsible; // resourceId
	private String taskOwner;
	private Date startDate;
	private String stringStartDate;
	private Date endDate;
	private String stringEndDate;
	private Date createDate;
	private String stringCreateDate;
	private Date updateDate;
	private String stringUpdateDate;
	private double estimateTime;
	private int actuallyTime;
	private int projectId;
	private int statusId;
	private int priorityId;
	private int typeId;
	private int completed;
	private String comment;
	private int totalCount;
	private int isTrash;
	private int clientId;
	private String text;
	private String startDateForGantt;
	private String endDateForGantt;
	private String duration;
	private float progress;
	private String assignee;
	private boolean open;
	private int parent;
	private String parentId;
	private int baselineId;

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getResponsible() {
		return responsible;
	}

	public void setResponsible(int responsible) {
		this.responsible = responsible;
	}

	public int getIsTrash() {
		return isTrash;
	}

	public void setIsTrash(int isTrash) {
		this.isTrash = isTrash;
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * @return the startDateForGantt
	 */
	public String getStartDateForGantt() {
		return dateFormat.format(startDate);
	}

	/**
	 * @return the startDateForGantt
	 */
	public boolean getOpen() {
		return true;
	}

	public String getDuration() {
		return String.valueOf(estimateTime);
	}

	public String getAssignee() {
		return assignee;
	}

	/**
	 * @return the endDateForGantt
	 */
	public String getEndDateForGantt() {
		return dateFormat.format(endDate);
	}

	public float getProgress() {
		if (completed == 0) {
			return 0;
		}
		int mod = Math.floorMod(completed, 100);
		if (mod > 0) {
			String result = String.valueOf("0." + mod);
			if(completed < 10){
				result = String.valueOf("0.0" + mod);
			}
			return Float.valueOf(result);
		}
		return 1;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return taskName;
	}

	public int getAffectationId() {
		return affectationId;
	}

	public void setAffectationId(int affectationId) {
		this.affectationId = affectationId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getWbs() {
		return wbs;
	}

	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
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

	public String getStringEndDate() {
		return stringEndDate;
	}

	public void setStringEndDate(String stringEndDate) {
		this.stringEndDate = stringEndDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStringCreateDate() {
		return stringCreateDate;
	}

	public void setStringCreateDate(String stringCreateDate) {
		this.stringCreateDate = stringCreateDate;
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

	public double getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(double estimateTime) {
		this.estimateTime = estimateTime;
	}

	public double getActuallyTime() {
		return actuallyTime;
	}

	public void setActuallyTime(int actuallyTime) {
		this.actuallyTime = actuallyTime;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public int rand(int min, int max) {
		try {
			Random rn = new Random();
			int range = max - min + 1;
			int randomNum = min + rn.nextInt(range);
			return randomNum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getBaselineId() {
		return baselineId;
	}

	public void setBaselineId(int baselineId) {
		this.baselineId = baselineId;
	}
}
