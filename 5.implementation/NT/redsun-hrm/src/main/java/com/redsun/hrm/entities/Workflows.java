package com.redsun.hrm.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Workflows entity
 */
@Component
@Scope("prototype")
public class Workflows extends AbstractEntity {

    // name
    private String name;
    // description
    private String description;
    // idle
	private Integer idle;
    // workflow_update
    private String workflowUpdate;
    // sort_order
    private Integer sortOrder;
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

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// workflowUpdate
    public void setWorkflowUpdate(final String workflowUpdate) {
        this.workflowUpdate = workflowUpdate;
    }
    public String getWorkflowUpdate() {
        return this.workflowUpdate;
    }

	// sortOrder
    public void setSortOrder(final Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getSortOrder() {
        return this.sortOrder;
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
        sb.append(description);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(workflowUpdate);
        sb.append("|");
        sb.append(sortOrder);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
