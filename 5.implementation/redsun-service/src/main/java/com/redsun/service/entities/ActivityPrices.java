package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ActivityPrices entity
 */
@Component
@Scope("prototype")
public class ActivityPrices extends AbstractEntity {

    // id_project
    private Integer idProject;
    // id_activity_type
    private Integer idActivityType;
    // name
    private String name;
    // price_cost
    private BigDecimal priceCost;
    // subcontractor
    private Integer subcontractor;
    // sort_order
    private Integer sortOrder;
    // idle
	private Integer idle;
    // subcontractor_cost
    private BigDecimal subcontractorCost;
    // id_team
    private Integer idTeam;
    // commission_cost
    private BigDecimal commissionCost;
    // is_ref
    private Integer isRef;
    // pct
    private Integer pct;
    // id_user
    private Integer idUser;
    // creation_date
    private Date creationDate;
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

	// idActivityType
    public void setIdActivityType(final Integer idActivityType) {
        this.idActivityType = idActivityType;
    }
    public Integer getIdActivityType() {
        return this.idActivityType;
    }

	// name
    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

	// priceCost
    public void setPriceCost(final BigDecimal priceCost) {
        this.priceCost = priceCost;
    }
    public BigDecimal getPriceCost() {
        return this.priceCost;
    }

	// subcontractor
    public void setSubcontractor(final Integer subcontractor) {
        this.subcontractor = subcontractor;
    }
    public Integer getSubcontractor() {
        return this.subcontractor;
    }

	// sortOrder
    public void setSortOrder(final Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getSortOrder() {
        return this.sortOrder;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// subcontractorCost
    public void setSubcontractorCost(final BigDecimal subcontractorCost) {
        this.subcontractorCost = subcontractorCost;
    }
    public BigDecimal getSubcontractorCost() {
        return this.subcontractorCost;
    }

	// idTeam
    public void setIdTeam(final Integer idTeam) {
        this.idTeam = idTeam;
    }
    public Integer getIdTeam() {
        return this.idTeam;
    }

	// commissionCost
    public void setCommissionCost(final BigDecimal commissionCost) {
        this.commissionCost = commissionCost;
    }
    public BigDecimal getCommissionCost() {
        return this.commissionCost;
    }

	// isRef
    public void setIsRef(final Integer isRef) {
        this.isRef = isRef;
    }
    public Integer getIsRef() {
        return this.isRef;
    }

	// pct
    public void setPct(final Integer pct) {
        this.pct = pct;
    }
    public Integer getPct() {
        return this.pct;
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
        sb.append(idActivityType);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(priceCost);
        sb.append("|");
        sb.append(subcontractor);
        sb.append("|");
        sb.append(sortOrder);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(subcontractorCost);
        sb.append("|");
        sb.append(idTeam);
        sb.append("|");
        sb.append(commissionCost);
        sb.append("|");
        sb.append(isRef);
        sb.append("|");
        sb.append(pct);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
