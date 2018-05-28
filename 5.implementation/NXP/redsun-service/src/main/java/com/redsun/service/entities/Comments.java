package com.redsun.service.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Comments extends AbstractEntity {
	private int taskId;
	private String content;
	private String commentBy;
	private Date createDateTime;
	private String stringCreateDateTime;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getStringCreateDateTime() {
		return stringCreateDateTime;
	}

	public void setStringCreateDateTime(String stringCreateDateTime) {
		this.stringCreateDateTime = stringCreateDateTime;
	}

}
