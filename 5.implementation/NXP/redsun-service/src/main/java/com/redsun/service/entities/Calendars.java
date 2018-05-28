package com.redsun.service.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Calendars extends AbstractEntity {

	private String title;
	private Date start;
	private String stringStartDate;
	private Date end;
	private String stringEndtDate;
	private String description;
	private String projectName;
	private int projectId;
	private String attachFile;
	private int clientId;

	public String getStringStartDate() {
		return stringStartDate;
	}

	public void setStringStartDate(String stringStartDate) {
		this.stringStartDate = stringStartDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getStringEndtDate() {
		return stringEndtDate;
	}

	public void setStringEndtDate(String stringEndtDate) {
		this.stringEndtDate = stringEndtDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
