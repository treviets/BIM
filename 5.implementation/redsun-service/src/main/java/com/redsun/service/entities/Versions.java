package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Versions entity
 */
@Component
@Scope("prototype")
public class Versions extends AbstractEntity {

    // id_product
    private Integer idProduct;
    // id_contact
    private Integer idContact;
    // id_resource
    private Integer idResource;
    // name
    private String name;
    // description
    private String description;
    // creation_date
    private Date creationDate;
    // idle
	private Integer idle;
    // initial_eis_date
    private Date initialEisDate;
    // planned_eis_date
    private Date plannedEisDate;
    // real_eis_date
    private Date realEisDate;
    // initial_end_date
    private Date initialEndDate;
    // planned_end_date
    private Date plannedEndDate;
    // real_end_date
    private Date realEndDate;
    // is_eis
	private Integer isEis;
    // scope
    private String scope;
    // version_number
    private String versionNumber;
    // id_user
    private Integer idUser;
    // id_version_type
    private Integer idVersionType;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// idProduct
    public void setIdProduct(final Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getIdProduct() {
        return this.idProduct;
    }

	// idContact
    public void setIdContact(final Integer idContact) {
        this.idContact = idContact;
    }
    public Integer getIdContact() {
        return this.idContact;
    }

	// idResource
    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
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

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// initialEisDate
    public void setInitialEisDate(final Date initialEisDate) {
        this.initialEisDate = initialEisDate;
    }
    public Date getInitialEisDate() {
        return this.initialEisDate;
    }

	// plannedEisDate
    public void setPlannedEisDate(final Date plannedEisDate) {
        this.plannedEisDate = plannedEisDate;
    }
    public Date getPlannedEisDate() {
        return this.plannedEisDate;
    }

	// realEisDate
    public void setRealEisDate(final Date realEisDate) {
        this.realEisDate = realEisDate;
    }
    public Date getRealEisDate() {
        return this.realEisDate;
    }

	// initialEndDate
    public void setInitialEndDate(final Date initialEndDate) {
        this.initialEndDate = initialEndDate;
    }
    public Date getInitialEndDate() {
        return this.initialEndDate;
    }

	// plannedEndDate
    public void setPlannedEndDate(final Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }
    public Date getPlannedEndDate() {
        return this.plannedEndDate;
    }

	// realEndDate
    public void setRealEndDate(final Date realEndDate) {
        this.realEndDate = realEndDate;
    }
    public Date getRealEndDate() {
        return this.realEndDate;
    }

	// isEis
    public void setIsEis(final Integer isEis) {
        this.isEis = isEis;
    }
    public Integer getIsEis() {
        return this.isEis;
    }

	// scope
    public void setScope(final String scope) {
        this.scope = scope;
    }
    public String getScope() {
        return this.scope;
    }

	// versionNumber
    public void setVersionNumber(final String versionNumber) {
        this.versionNumber = versionNumber;
    }
    public String getVersionNumber() {
        return this.versionNumber;
    }

	// idUser
    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

	// idVersionType
    public void setIdVersionType(final Integer idVersionType) {
        this.idVersionType = idVersionType;
    }
    public Integer getIdVersionType() {
        return this.idVersionType;
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
        sb.append(idProduct);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(initialEisDate);
        sb.append("|");
        sb.append(plannedEisDate);
        sb.append("|");
        sb.append(realEisDate);
        sb.append("|");
        sb.append(initialEndDate);
        sb.append("|");
        sb.append(plannedEndDate);
        sb.append("|");
        sb.append(realEndDate);
        sb.append("|");
        sb.append(isEis);
        sb.append("|");
        sb.append(scope);
        sb.append("|");
        sb.append(versionNumber);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(idVersionType);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
