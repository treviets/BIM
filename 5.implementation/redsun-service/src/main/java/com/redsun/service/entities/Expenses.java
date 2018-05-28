package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Expenses entity
 */
@Component
@Scope("prototype")
public class Expenses extends AbstractEntity {

    private Integer idProject;
    private Integer idResource;
    private Integer idUser;
    private Integer idExpenseType;
    private String scope;
    private String name;
    private Integer idStatus;
    private String description;
    private Date expensePlannedDate;
    private Date expenseRealDate;
    private BigDecimal plannedAmount;
    private BigDecimal realAmount;
    private String day;
    private String week;
    private String month;
    private String year;
	private Integer idle;
    private String reference;
    private String externalReference;
	private Integer cancelled;
    private Integer idDocument;
    private Integer idProvider;
    private Date sendDate;
    private Integer idDeliveryMode;
    private String deliveryDelay;
    private Date deliveryDate;
    private String paymentCondition;
    private Date receptionDate;
    private String result;
    private BigDecimal taxPct;
    private BigDecimal plannedFullAmount;
    private BigDecimal realFullAmount;
    private Date idleDate;
	private Integer handled;
    private Date handledDate;
	private Integer done;
    private Date doneDate;
    private Integer idResponsible;
	private Integer paymentDone;
    private Integer idContact;
    private Integer clientId;
	private Integer totalCount;

    public void setIdProject(final Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdProject() {
        return this.idProject;
    }

    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
    }

    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdExpenseType(final Integer idExpenseType) {
        this.idExpenseType = idExpenseType;
    }
    public Integer getIdExpenseType() {
        return this.idExpenseType;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }
    public String getScope() {
        return this.scope;
    }

    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setIdStatus(final Integer idStatus) {
        this.idStatus = idStatus;
    }
    public Integer getIdStatus() {
        return this.idStatus;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setExpensePlannedDate(final Date expensePlannedDate) {
        this.expensePlannedDate = expensePlannedDate;
    }
    public Date getExpensePlannedDate() {
        return this.expensePlannedDate;
    }

    public void setExpenseRealDate(final Date expenseRealDate) {
        this.expenseRealDate = expenseRealDate;
    }
    public Date getExpenseRealDate() {
        return this.expenseRealDate;
    }

    public void setPlannedAmount(final BigDecimal plannedAmount) {
        this.plannedAmount = plannedAmount;
    }
    public BigDecimal getPlannedAmount() {
        return this.plannedAmount;
    }

    public void setRealAmount(final BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
    public BigDecimal getRealAmount() {
        return this.realAmount;
    }

    public void setDay(final String day) {
        this.day = day;
    }
    public String getDay() {
        return this.day;
    }

    public void setWeek(final String week) {
        this.week = week;
    }
    public String getWeek() {
        return this.week;
    }

    public void setMonth(final String month) {
        this.month = month;
    }
    public String getMonth() {
        return this.month;
    }

    public void setYear(final String year) {
        this.year = year;
    }
    public String getYear() {
        return this.year;
    }

    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

    public void setExternalReference(final String externalReference) {
        this.externalReference = externalReference;
    }
    public String getExternalReference() {
        return this.externalReference;
    }

    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
    }

    public void setIdDocument(final Integer idDocument) {
        this.idDocument = idDocument;
    }
    public Integer getIdDocument() {
        return this.idDocument;
    }

    public void setIdProvider(final Integer idProvider) {
        this.idProvider = idProvider;
    }
    public Integer getIdProvider() {
        return this.idProvider;
    }

    public void setSendDate(final Date sendDate) {
        this.sendDate = sendDate;
    }
    public Date getSendDate() {
        return this.sendDate;
    }

    public void setIdDeliveryMode(final Integer idDeliveryMode) {
        this.idDeliveryMode = idDeliveryMode;
    }
    public Integer getIdDeliveryMode() {
        return this.idDeliveryMode;
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

    public void setReceptionDate(final Date receptionDate) {
        this.receptionDate = receptionDate;
    }
    public Date getReceptionDate() {
        return this.receptionDate;
    }

    public void setResult(final String result) {
        this.result = result;
    }
    public String getResult() {
        return this.result;
    }

    public void setTaxPct(final BigDecimal taxPct) {
        this.taxPct = taxPct;
    }
    public BigDecimal getTaxPct() {
        return this.taxPct;
    }

    public void setPlannedFullAmount(final BigDecimal plannedFullAmount) {
        this.plannedFullAmount = plannedFullAmount;
    }
    public BigDecimal getPlannedFullAmount() {
        return this.plannedFullAmount;
    }

    public void setRealFullAmount(final BigDecimal realFullAmount) {
        this.realFullAmount = realFullAmount;
    }
    public BigDecimal getRealFullAmount() {
        return this.realFullAmount;
    }

    public void setIdleDate(final Date idleDate) {
        this.idleDate = idleDate;
    }
    public Date getIdleDate() {
        return this.idleDate;
    }

    public void setHandled(final Integer handled) {
        this.handled = handled;
    }
    public Integer getHandled() {
        return this.handled;
    }

    public void setHandledDate(final Date handledDate) {
        this.handledDate = handledDate;
    }
    public Date getHandledDate() {
        return this.handledDate;
    }

    public void setDone(final Integer done) {
        this.done = done;
    }
    public Integer getDone() {
        return this.done;
    }

    public void setDoneDate(final Date doneDate) {
        this.doneDate = doneDate;
    }
    public Date getDoneDate() {
        return this.doneDate;
    }

    public void setIdResponsible(final Integer idResponsible) {
        this.idResponsible = idResponsible;
    }
    public Integer getIdResponsible() {
        return this.idResponsible;
    }

    public void setPaymentDone(final Integer paymentDone) {
        this.paymentDone = paymentDone;
    }
    public Integer getPaymentDone() {
        return this.paymentDone;
    }

    public void setIdContact(final Integer idContact) {
        this.idContact = idContact;
    }
    public Integer getIdContact() {
        return this.idContact;
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
        sb.append(idProject);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(idExpenseType);
        sb.append("|");
        sb.append(scope);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(idStatus);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(expensePlannedDate);
        sb.append("|");
        sb.append(expenseRealDate);
        sb.append("|");
        sb.append(plannedAmount);
        sb.append("|");
        sb.append(realAmount);
        sb.append("|");
        sb.append(day);
        sb.append("|");
        sb.append(week);
        sb.append("|");
        sb.append(month);
        sb.append("|");
        sb.append(year);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(reference);
        sb.append("|");
        sb.append(externalReference);
        sb.append("|");
        sb.append(cancelled);
        sb.append("|");
        sb.append(idDocument);
        sb.append("|");
        sb.append(idProvider);
        sb.append("|");
        sb.append(sendDate);
        sb.append("|");
        sb.append(idDeliveryMode);
        sb.append("|");
        sb.append(deliveryDelay);
        sb.append("|");
        sb.append(deliveryDate);
        sb.append("|");
        sb.append(paymentCondition);
        sb.append("|");
        sb.append(receptionDate);
        sb.append("|");
        sb.append(result);
        sb.append("|");
        sb.append(taxPct);
        sb.append("|");
        sb.append(plannedFullAmount);
        sb.append("|");
        sb.append(realFullAmount);
        sb.append("|");
        sb.append(idleDate);
        sb.append("|");
        sb.append(handled);
        sb.append("|");
        sb.append(handledDate);
        sb.append("|");
        sb.append(done);
        sb.append("|");
        sb.append(doneDate);
        sb.append("|");
        sb.append(idResponsible);
        sb.append("|");
        sb.append(paymentDone);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
