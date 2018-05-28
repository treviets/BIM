package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * VersionProjects entity
 */
@Component
@Scope("prototype")
public class VersionProjects extends AbstractEntity {

    // id_project
    private Integer idProject;
    // id_version
    private Integer idVersion;
    // start_date
    private Date startDate;
    // end_date
    private Date endDate;
    // idle
	private Integer idle;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// idProject
    public void setIdProject(final Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdProject() {
        return this.idProject;
    }

	// idVersion
    public void setIdVersion(final Integer idVersion) {
        this.idVersion = idVersion;
    }
    public Integer getIdVersion() {
        return this.idVersion;
    }

	// startDate
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }

	// endDate
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
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
        sb.append(idProject);
        sb.append("|");
        sb.append(idVersion);
        sb.append("|");
        sb.append(startDate);
        sb.append("|");
        sb.append(endDate);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
