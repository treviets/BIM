package com.redsun.service.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CallForTenders entity
 */
public class CallForTenders extends AbstractEntity {

    private String reference;
    private String name;
    private Integer idCallForTenderType;
    private Integer idProject;
    private Integer idUser;
    private String description;
    private String technicalRequirements;
    private String businessRequirements;
    private String otherRequirements;
    private Date creationDate;
    private Integer idStatus;
    private Integer idResource;
    private Date sendDateTime;
    private Date expectedTenderDateTime;
    private BigDecimal maxAmount;
    private Date deliveryDate;
    private BigDecimal evaluationMaxValue;
	private Integer fixValue;
    private Integer idProduct;
    private Integer idProductVersion;
    private Integer idComponent;
    private Integer idComponentVersion;
    private String result;
	private Integer handled;
	private Integer done;
	private Integer idle;
	private Integer cancelled;
    private Date handledDate;
    private Date doneDate;
    private Date idleDate;
    private Integer clientId;
	private Integer totalCount;

	// reference
    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

	// name
    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

	// idCallForTenderType
    public void setIdCallForTenderType(final Integer idCallForTenderType) {
        this.idCallForTenderType = idCallForTenderType;
    }
    public Integer getIdCallForTenderType() {
        return this.idCallForTenderType;
    }

	// idProject
    public void setIdProject(final Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdProject() {
        return this.idProject;
    }

	// idUser
    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// technicalRequirements
    public void setTechnicalRequirements(final String technicalRequirements) {
        this.technicalRequirements = technicalRequirements;
    }
    public String getTechnicalRequirements() {
        return this.technicalRequirements;
    }

	// businessRequirements
    public void setBusinessRequirements(final String businessRequirements) {
        this.businessRequirements = businessRequirements;
    }
    public String getBusinessRequirements() {
        return this.businessRequirements;
    }

	// otherRequirements
    public void setOtherRequirements(final String otherRequirements) {
        this.otherRequirements = otherRequirements;
    }
    public String getOtherRequirements() {
        return this.otherRequirements;
    }

	// creationDate
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
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

	// sendDateTime
    public void setSendDateTime(final Date sendDateTime) {
        this.sendDateTime = sendDateTime;
    }
    public Date getSendDateTime() {
        return this.sendDateTime;
    }

	// expectedTenderDateTime
    public void setExpectedTenderDateTime(final Date expectedTenderDateTime) {
        this.expectedTenderDateTime = expectedTenderDateTime;
    }
    public Date getExpectedTenderDateTime() {
        return this.expectedTenderDateTime;
    }

	// maxAmount
    public void setMaxAmount(final BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
    public BigDecimal getMaxAmount() {
        return this.maxAmount;
    }

	// deliveryDate
    public void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

	// evaluationMaxValue
    public void setEvaluationMaxValue(final BigDecimal evaluationMaxValue) {
        this.evaluationMaxValue = evaluationMaxValue;
    }
    public BigDecimal getEvaluationMaxValue() {
        return this.evaluationMaxValue;
    }

	// fixValue
    public void setFixValue(final Integer fixValue) {
        this.fixValue = fixValue;
    }
    public Integer getFixValue() {
        return this.fixValue;
    }

	// idProduct
    public void setIdProduct(final Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getIdProduct() {
        return this.idProduct;
    }

	// idProductVersion
    public void setIdProductVersion(final Integer idProductVersion) {
        this.idProductVersion = idProductVersion;
    }
    public Integer getIdProductVersion() {
        return this.idProductVersion;
    }

	// idComponent
    public void setIdComponent(final Integer idComponent) {
        this.idComponent = idComponent;
    }
    public Integer getIdComponent() {
        return this.idComponent;
    }

	// idComponentVersion
    public void setIdComponentVersion(final Integer idComponentVersion) {
        this.idComponentVersion = idComponentVersion;
    }
    public Integer getIdComponentVersion() {
        return this.idComponentVersion;
    }

	// result
    public void setResult(final String result) {
        this.result = result;
    }
    public String getResult() {
        return this.result;
    }

	// handled
    public void setHandled(final Integer handled) {
        this.handled = handled;
    }
    public Integer getHandled() {
        return this.handled;
    }

	// done
    public void setDone(final Integer done) {
        this.done = done;
    }
    public Integer getDone() {
        return this.done;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// cancelled
    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
    }

	// handledDate
    public void setHandledDate(final Date handledDate) {
        this.handledDate = handledDate;
    }
    public Date getHandledDate() {
        return this.handledDate;
    }

	// doneDate
    public void setDoneDate(final Date doneDate) {
        this.doneDate = doneDate;
    }
    public Date getDoneDate() {
        return this.doneDate;
    }

	// idleDate
    public void setIdleDate(final Date idleDate) {
        this.idleDate = idleDate;
    }
    public Date getIdleDate() {
        return this.idleDate;
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
        sb.append(reference);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(idCallForTenderType);
        sb.append("|");
        sb.append(idProject);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(technicalRequirements);
        sb.append("|");
        sb.append(businessRequirements);
        sb.append("|");
        sb.append(otherRequirements);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(idStatus);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(sendDateTime);
        sb.append("|");
        sb.append(expectedTenderDateTime);
        sb.append("|");
        sb.append(maxAmount);
        sb.append("|");
        sb.append(deliveryDate);
        sb.append("|");
        sb.append(evaluationMaxValue);
        sb.append("|");
        sb.append(fixValue);
        sb.append("|");
        sb.append(idProduct);
        sb.append("|");
        sb.append(idProductVersion);
        sb.append("|");
        sb.append(idComponent);
        sb.append("|");
        sb.append(idComponentVersion);
        sb.append("|");
        sb.append(result);
        sb.append("|");
        sb.append(handled);
        sb.append("|");
        sb.append(done);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(cancelled);
        sb.append("|");
        sb.append(handledDate);
        sb.append("|");
        sb.append(doneDate);
        sb.append("|");
        sb.append(idleDate);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
