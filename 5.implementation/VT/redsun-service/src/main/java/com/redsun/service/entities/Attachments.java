package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Attachments entity
 */
@Component
@Scope("prototype")
public class Attachments extends AbstractEntity {

    // ref_type
    private String refType;
    // ref_id
    private Integer refId;
    // id_user
    private Integer idUser;
    // creation_date
    private Date creationDate;
    // file_name
    private String fileName;
    // description
    private String description;
    // sub_directory
    private String subDirectory;
    // mime_type
    private String mimeType;
    // file_size
    private Integer fileSize;
    // link
    private String link;
    // type
    private String type;
    // id_privacy
    private Integer idPrivacy;
    // id_team
    private Integer idTeam;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// refType
    public void setRefType(final String refType) {
        this.refType = refType;
    }
    public String getRefType() {
        return this.refType;
    }

	// refId
    public void setRefId(final Integer refId) {
        this.refId = refId;
    }
    public Integer getRefId() {
        return this.refId;
    }

	// idUser
    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

	// creationDate
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }

	// fileName
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// subDirectory
    public void setSubDirectory(final String subDirectory) {
        this.subDirectory = subDirectory;
    }
    public String getSubDirectory() {
        return this.subDirectory;
    }

	// mimeType
    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }
    public String getMimeType() {
        return this.mimeType;
    }

	// fileSize
    public void setFileSize(final Integer fileSize) {
        this.fileSize = fileSize;
    }
    public Integer getFileSize() {
        return this.fileSize;
    }

	// link
    public void setLink(final String link) {
        this.link = link;
    }
    public String getLink() {
        return this.link;
    }

	// type
    public void setType(final String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }

	// idPrivacy
    public void setIdPrivacy(final Integer idPrivacy) {
        this.idPrivacy = idPrivacy;
    }
    public Integer getIdPrivacy() {
        return this.idPrivacy;
    }

	// idTeam
    public void setIdTeam(final Integer idTeam) {
        this.idTeam = idTeam;
    }
    public Integer getIdTeam() {
        return this.idTeam;
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
        sb.append(refType);
        sb.append("|");
        sb.append(refId);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(fileName);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(subDirectory);
        sb.append("|");
        sb.append(mimeType);
        sb.append("|");
        sb.append(fileSize);
        sb.append("|");
        sb.append(link);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(idPrivacy);
        sb.append("|");
        sb.append(idTeam);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
