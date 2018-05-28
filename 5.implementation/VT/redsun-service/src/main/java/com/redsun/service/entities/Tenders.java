package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tender table
 */
@Component
@Scope("prototype")
public class Tenders extends AbstractEntity {

    private String reference;
    private String name;
    private Integer idTenderType;
    private Integer idProject;
    private Integer idCallForTender;
    private Integer idUser;
    private Date creationDate;
    private Integer idProvider;
    private String externalReference;
    private String description;
    private Integer idStatus;
    private Integer idResource;
    private Integer idContact;
    private Date requestDateTime;
    private Date expectedTenderDateTime;
    private Date receptionDateTime;
    private BigDecimal evaluationValue;
    private Integer evaluationRank;
    private Date offerValidityEndDate;
    private BigDecimal plannedAmount;
    private BigDecimal taxPct;
    private BigDecimal plannedTaxAmount;
    private BigDecimal plannedFullAmount;
    private BigDecimal initialAmount;
    private BigDecimal initialTaxAmount;
    private BigDecimal initialFullAmount;
    private String deliveryDelay;
    private Date deliveryDate;
    private String paymentCondition;
    private BigDecimal evaluation;
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

    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setIdTenderType(final Integer idTenderType) {
        this.idTenderType = idTenderType;
    }
    public Integer getIdTenderType() {
        return this.idTenderType;
    }

    public void setIdProject(final Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdProject() {
        return this.idProject;
    }

    public void setIdCallForTender(final Integer idCallForTender) {
        this.idCallForTender = idCallForTender;
    }
    public Integer getIdCallForTender() {
        return this.idCallForTender;
    }

    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setIdProvider(final Integer idProvider) {
        this.idProvider = idProvider;
    }
    public Integer getIdProvider() {
        return this.idProvider;
    }

    public void setExternalReference(final String externalReference) {
        this.externalReference = externalReference;
    }
    public String getExternalReference() {
        return this.externalReference;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setIdStatus(final Integer idStatus) {
        this.idStatus = idStatus;
    }
    public Integer getIdStatus() {
        return this.idStatus;
    }

    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
    }

    public void setIdContact(final Integer idContact) {
        this.idContact = idContact;
    }
    public Integer getIdContact() {
        return this.idContact;
    }

    public void setRequestDateTime(final Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
    public Date getRequestDateTime() {
        return this.requestDateTime;
    }

    public void setExpectedTenderDateTime(final Date expectedTenderDateTime) {
        this.expectedTenderDateTime = expectedTenderDateTime;
    }
    public Date getExpectedTenderDateTime() {
        return this.expectedTenderDateTime;
    }

    public void setReceptionDateTime(final Date receptionDateTime) {
        this.receptionDateTime = receptionDateTime;
    }
    public Date getReceptionDateTime() {
        return this.receptionDateTime;
    }

    public void setEvaluationValue(final BigDecimal evaluationValue) {
        this.evaluationValue = evaluationValue;
    }
    public BigDecimal getEvaluationValue() {
        return this.evaluationValue;
    }

    public void setEvaluationRank(final Integer evaluationRank) {
        this.evaluationRank = evaluationRank;
    }
    public Integer getEvaluationRank() {
        return this.evaluationRank;
    }

    public void setOfferValidityEndDate(final Date offerValidityEndDate) {
        this.offerValidityEndDate = offerValidityEndDate;
    }
    public Date getOfferValidityEndDate() {
        return this.offerValidityEndDate;
    }

    public void setPlannedAmount(final BigDecimal plannedAmount) {
        this.plannedAmount = plannedAmount;
    }
    public BigDecimal getPlannedAmount() {
        return this.plannedAmount;
    }

    public void setTaxPct(final BigDecimal taxPct) {
        this.taxPct = taxPct;
    }
    public BigDecimal getTaxPct() {
        return this.taxPct;
    }

    public void setPlannedTaxAmount(final BigDecimal plannedTaxAmount) {
        this.plannedTaxAmount = plannedTaxAmount;
    }
    public BigDecimal getPlannedTaxAmount() {
        return this.plannedTaxAmount;
    }

    public void setPlannedFullAmount(final BigDecimal plannedFullAmount) {
        this.plannedFullAmount = plannedFullAmount;
    }
    public BigDecimal getPlannedFullAmount() {
        return this.plannedFullAmount;
    }

    public void setInitialAmount(final BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }
    public BigDecimal getInitialAmount() {
        return this.initialAmount;
    }

    public void setInitialTaxAmount(final BigDecimal initialTaxAmount) {
        this.initialTaxAmount = initialTaxAmount;
    }
    public BigDecimal getInitialTaxAmount() {
        return this.initialTaxAmount;
    }

    public void setInitialFullAmount(final BigDecimal initialFullAmount) {
        this.initialFullAmount = initialFullAmount;
    }
    public BigDecimal getInitialFullAmount() {
        return this.initialFullAmount;
    }

    public void setDeliveryDelay(final String deliveryDelay) {
        this.deliveryDelay = deliveryDelay;
    }
    public String getDeliveryDelay() {
        return this.deliveryDelay;
    }

    public void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setPaymentCondition(final String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }
    public String getPaymentCondition() {
        return this.paymentCondition;
    }

    public void setEvaluation(final BigDecimal evaluation) {
        this.evaluation = evaluation;
    }
    public BigDecimal getEvaluation() {
        return this.evaluation;
    }

    public void setResult(final String result) {
        this.result = result;
    }
    public String getResult() {
        return this.result;
    }

    public void setHandled(final Integer handled) {
        this.handled = handled;
    }
    public Integer getHandled() {
        return this.handled;
    }

    public void setDone(final Integer done) {
        this.done = done;
    }
    public Integer getDone() {
        return this.done;
    }

    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
    }

    public void setHandledDate(final Date handledDate) {
        this.handledDate = handledDate;
    }
    public Date getHandledDate() {
        return this.handledDate;
    }

    public void setDoneDate(final Date doneDate) {
        this.doneDate = doneDate;
    }
    public Date getDoneDate() {
        return this.doneDate;
    }

    public void setIdleDate(final Date idleDate) {
        this.idleDate = idleDate;
    }
    public Date getIdleDate() {
        return this.idleDate;
    }

    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }
    public Integer getClientId() {
        return this.clientId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(reference);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(idTenderType);
        sb.append("|");
        sb.append(idProject);
        sb.append("|");
        sb.append(idCallForTender);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(idProvider);
        sb.append("|");
        sb.append(externalReference);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(idStatus);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(requestDateTime);
        sb.append("|");
        sb.append(expectedTenderDateTime);
        sb.append("|");
        sb.append(receptionDateTime);
        sb.append("|");
        sb.append(evaluationValue);
        sb.append("|");
        sb.append(evaluationRank);
        sb.append("|");
        sb.append(offerValidityEndDate);
        sb.append("|");
        sb.append(plannedAmount);
        sb.append("|");
        sb.append(taxPct);
        sb.append("|");
        sb.append(plannedTaxAmount);
        sb.append("|");
        sb.append(plannedFullAmount);
        sb.append("|");
        sb.append(initialAmount);
        sb.append("|");
        sb.append(initialTaxAmount);
        sb.append("|");
        sb.append(initialFullAmount);
        sb.append("|");
        sb.append(deliveryDelay);
        sb.append("|");
        sb.append(deliveryDate);
        sb.append("|");
        sb.append(paymentCondition);
        sb.append("|");
        sb.append(evaluation);
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
