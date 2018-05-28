package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Milestones entity
 */
@Component
@Scope("prototype")
public class Milestones extends AbstractEntity {

    // id_project
    private Integer idProject;
    // name
    private String name;
    // description
    private String description;
    // creation_date
    private Date creationDate;
    // id_user
    private Integer idUser;
    // id_status
    private Integer idStatus;
    // id_resource
    private Integer idResource;
    // result
    private String result;
    // comment
    private String comment;
    // idle
	private Integer idle;
    // id_milestone_type
    private Integer idMilestoneType;
    // id_activity
    private Integer idActivity;
    // done
	private Integer done;
    // idle_date
    private Date idleDate;
    // done_date
    private Date doneDate;
    // handled
	private Integer handled;
    // handled_date
    private Date handledDate;
    // id_version
    private Integer idVersion;
    // reference
    private String reference;
    // external_reference
    private String externalReference;
    // cancelled
	private Integer cancelled;
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

	// name
    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// creationDate
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }

	// idUser
    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

	// idStatus
    public void setIdStatus(final Integer idStatus) {
        this.idStatus = idStatus;
    }
    public Integer getIdStatus() {
        return this.idStatus;
    }

	// idResource
    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
    }

	// result
    public void setResult(final String result) {
        this.result = result;
    }
    public String getResult() {
        return this.result;
    }

	// comment
    public void setComment(final String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return this.comment;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// idMilestoneType
    public void setIdMilestoneType(final Integer idMilestoneType) {
        this.idMilestoneType = idMilestoneType;
    }
    public Integer getIdMilestoneType() {
        return this.idMilestoneType;
    }

	// idActivity
    public void setIdActivity(final Integer idActivity) {
        this.idActivity = idActivity;
    }
    public Integer getIdActivity() {
        return this.idActivity;
    }

	// done
    public void setDone(final Integer done) {
        this.done = done;
    }
    public Integer getDone() {
        return this.done;
    }

	// idleDate
    public void setIdleDate(final Date idleDate) {
        this.idleDate = idleDate;
    }
    public Date getIdleDate() {
        return this.idleDate;
    }

	// doneDate
    public void setDoneDate(final Date doneDate) {
        this.doneDate = doneDate;
    }
    public Date getDoneDate() {
        return this.doneDate;
    }

	// handled
    public void setHandled(final Integer handled) {
        this.handled = handled;
    }
    public Integer getHandled() {
        return this.handled;
    }

	// handledDate
    public void setHandledDate(final Date handledDate) {
        this.handledDate = handledDate;
    }
    public Date getHandledDate() {
        return this.handledDate;
    }

	// idVersion
    public void setIdVersion(final Integer idVersion) {
        this.idVersion = idVersion;
    }
    public Integer getIdVersion() {
        return this.idVersion;
    }

	// reference
    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

	// externalReference
    public void setExternalReference(final String externalReference) {
        this.externalReference = externalReference;
    }
    public String getExternalReference() {
        return this.externalReference;
    }

	// cancelled
    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
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
        sb.append(name);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(idStatus);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(result);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(idMilestoneType);
        sb.append("|");
        sb.append(idActivity);
        sb.append("|");
        sb.append(done);
        sb.append("|");
        sb.append(idleDate);
        sb.append("|");
        sb.append(doneDate);
        sb.append("|");
        sb.append(handled);
        sb.append("|");
        sb.append(handledDate);
        sb.append("|");
        sb.append(idVersion);
        sb.append("|");
        sb.append(reference);
        sb.append("|");
        sb.append(externalReference);
        sb.append("|");
        sb.append(cancelled);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
