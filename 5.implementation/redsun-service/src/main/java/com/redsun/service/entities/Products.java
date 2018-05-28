package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Products entity
 */
@Component
@Scope("prototype")
public class Products extends AbstractEntity {

    // name
    private String name;
    // id_client
    private Integer idClient;
    // id_contact
    private Integer idContact;
    // description
    private String description;
    // creation_date
    private Date creationDate;
    // idle
	private Integer idle;
    // id_product
    private Integer idProduct;
    // designation
    private String designation;
    // scope
    private String scope;
    // id_product_type
    private Integer idProductType;
    // id_component_type
    private Integer idComponentType;
    // id_resource
    private Integer idResource;
    // id_user
    private Integer idUser;
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

	// idClient
    public void setIdClient(final Integer idClient) {
        this.idClient = idClient;
    }
    public Integer getIdClient() {
        return this.idClient;
    }

	// idContact
    public void setIdContact(final Integer idContact) {
        this.idContact = idContact;
    }
    public Integer getIdContact() {
        return this.idContact;
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

	// idProduct
    public void setIdProduct(final Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getIdProduct() {
        return this.idProduct;
    }

	// designation
    public void setDesignation(final String designation) {
        this.designation = designation;
    }
    public String getDesignation() {
        return this.designation;
    }

	// scope
    public void setScope(final String scope) {
        this.scope = scope;
    }
    public String getScope() {
        return this.scope;
    }

	// idProductType
    public void setIdProductType(final Integer idProductType) {
        this.idProductType = idProductType;
    }
    public Integer getIdProductType() {
        return this.idProductType;
    }

	// idComponentType
    public void setIdComponentType(final Integer idComponentType) {
        this.idComponentType = idComponentType;
    }
    public Integer getIdComponentType() {
        return this.idComponentType;
    }

	// idResource
    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
    }

	// idUser
    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
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
        sb.append(idClient);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(idProduct);
        sb.append("|");
        sb.append(designation);
        sb.append("|");
        sb.append(scope);
        sb.append("|");
        sb.append(idProductType);
        sb.append("|");
        sb.append(idComponentType);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
