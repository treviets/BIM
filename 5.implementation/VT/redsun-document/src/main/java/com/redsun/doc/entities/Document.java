package com.redsun.doc.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/* GeneralInformation entity
 * Represent general information each of phase of a process
 */
@Component
@Scope("prototype")
public class Document extends AbstractEntity {
	
	private String name;
	private String location;
	private int projectId;
	private int clientId;
	private String refType;
	private int directoryId;
	private String mimeType;
	private int fileSize;
	private String link;
	private Integer documentType;
	private int documentVersionId;
	private short locked;
	private String description;
	private String userName;
	private Date createDateTime;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
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
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the refType
	 */
	public String getRefType() {
		return refType;
	}
	/**
	 * @param refType the refType to set
	 */
	public void setRefType(String refType) {
		this.refType = refType;
	}
	/**
	 * @return the directoryId
	 */
	public int getDirectoryId() {
		return directoryId;
	}
	/**
	 * @param directoryId the directoryId to set
	 */
	public void setDirectoryId(int directoryId) {
		this.directoryId = directoryId;
	}
	/**
	 * @return the mime_type
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * @param mime_type the mime_type to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the documentType
	 */
	public Integer getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the documentVersionId
	 */
	public int getDocumentVersionId() {
		return documentVersionId;
	}
	/**
	 * @param documentVersionId the documentVersionId to set
	 */
	public void setDocumentVersionId(int documentVersionId) {
		this.documentVersionId = documentVersionId;
	}
	/**
	 * @return the locked
	 */
	public short getLocked() {
		return locked;
	}
	/**
	 * @param locked the locked to set
	 */
	public void setLocked(short locked) {
		this.locked = locked;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
}

