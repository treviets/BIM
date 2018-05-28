package com.redsun.service.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Commands entity
 */
@Component
@Scope("prototype")
public class Commands extends AbstractEntity {

    // id_project
    private Integer idProject;
    // id_command_type
    private Integer idCommandType;
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
    // additional_info
    private String additionalInfo;
    // external_reference
    private String externalReference;
    // id_activity
    private Integer idActivity;
    // initial_start_date
    private Date initialStartDate;
    // initial_end_date
    private Date initialEndDate;
    // validated_end_date
    private Date validatedEndDate;
    // initial_work
    private BigDecimal initialWork;
    // initial_price_per_day_amount
    private BigDecimal initialPricePerDayAmount;
    // untaxed_amount
    private BigDecimal untaxedAmount;
    // add_work
    private BigDecimal addWork;
    // add_price_per_day_amount
    private BigDecimal addPricePerDayAmount;
    // add_untaxed_amount
    private BigDecimal addUntaxedAmount;
    // validated_work
    private BigDecimal validatedWork;
    // validated_price_per_day_amount
    private BigDecimal validatedPricePerDayAmount;
    // total_untaxed_amount
    private BigDecimal totalUntaxedAmount;
    // comment
    private String comment;
    // idle
	private Integer idle;
    // done
	private Integer done;
    // cancelled
	private Integer cancelled;
    // idle_date
    private Date idleDate;
    // done_date
    private Date doneDate;
    // handled
	private Integer handled;
    // handled_date
    private Date handledDate;
    // reference
    private String reference;
    // validated_start_date
    private Date validatedStartDate;
    // id_activity_type
    private Integer idActivityType;
    // id_client
    private Integer idClient;
    // id_contact
    private Integer idContact;
    // id_payment_delay
    private Integer idPaymentDelay;
    // tax
    private BigDecimal tax;
    // full_amount
    private BigDecimal fullAmount;
    // add_full_amount
    private BigDecimal addFullAmount;
    // total_full_amount
    private BigDecimal totalFullAmount;
    // id_delivery_mode
    private Integer idDeliveryMode;
    // reception_date
    private Date receptionDate;
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

	// idCommandType
    public void setIdCommandType(final Integer idCommandType) {
        this.idCommandType = idCommandType;
    }
    public Integer getIdCommandType() {
        return this.idCommandType;
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

	// additionalInfo
    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

	// externalReference
    public void setExternalReference(final String externalReference) {
        this.externalReference = externalReference;
    }
    public String getExternalReference() {
        return this.externalReference;
    }

	// idActivity
    public void setIdActivity(final Integer idActivity) {
        this.idActivity = idActivity;
    }
    public Integer getIdActivity() {
        return this.idActivity;
    }

	// initialStartDate
    public void setInitialStartDate(final Date initialStartDate) {
        this.initialStartDate = initialStartDate;
    }
    public Date getInitialStartDate() {
        return this.initialStartDate;
    }

	// initialEndDate
    public void setInitialEndDate(final Date initialEndDate) {
        this.initialEndDate = initialEndDate;
    }
    public Date getInitialEndDate() {
        return this.initialEndDate;
    }

	// validatedEndDate
    public void setValidatedEndDate(final Date validatedEndDate) {
        this.validatedEndDate = validatedEndDate;
    }
    public Date getValidatedEndDate() {
        return this.validatedEndDate;
    }

	// initialWork
    public void setInitialWork(final BigDecimal initialWork) {
        this.initialWork = initialWork;
    }
    public BigDecimal getInitialWork() {
        return this.initialWork;
    }

	// initialPricePerDayAmount
    public void setInitialPricePerDayAmount(final BigDecimal initialPricePerDayAmount) {
        this.initialPricePerDayAmount = initialPricePerDayAmount;
    }
    public BigDecimal getInitialPricePerDayAmount() {
        return this.initialPricePerDayAmount;
    }

	// untaxedAmount
    public void setUntaxedAmount(final BigDecimal untaxedAmount) {
        this.untaxedAmount = untaxedAmount;
    }
    public BigDecimal getUntaxedAmount() {
        return this.untaxedAmount;
    }

	// addWork
    public void setAddWork(final BigDecimal addWork) {
        this.addWork = addWork;
    }
    public BigDecimal getAddWork() {
        return this.addWork;
    }

	// addPricePerDayAmount
    public void setAddPricePerDayAmount(final BigDecimal addPricePerDayAmount) {
        this.addPricePerDayAmount = addPricePerDayAmount;
    }
    public BigDecimal getAddPricePerDayAmount() {
        return this.addPricePerDayAmount;
    }

	// addUntaxedAmount
    public void setAddUntaxedAmount(final BigDecimal addUntaxedAmount) {
        this.addUntaxedAmount = addUntaxedAmount;
    }
    public BigDecimal getAddUntaxedAmount() {
        return this.addUntaxedAmount;
    }

	// validatedWork
    public void setValidatedWork(final BigDecimal validatedWork) {
        this.validatedWork = validatedWork;
    }
    public BigDecimal getValidatedWork() {
        return this.validatedWork;
    }

	// validatedPricePerDayAmount
    public void setValidatedPricePerDayAmount(final BigDecimal validatedPricePerDayAmount) {
        this.validatedPricePerDayAmount = validatedPricePerDayAmount;
    }
    public BigDecimal getValidatedPricePerDayAmount() {
        return this.validatedPricePerDayAmount;
    }

	// totalUntaxedAmount
    public void setTotalUntaxedAmount(final BigDecimal totalUntaxedAmount) {
        this.totalUntaxedAmount = totalUntaxedAmount;
    }
    public BigDecimal getTotalUntaxedAmount() {
        return this.totalUntaxedAmount;
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

	// done
    public void setDone(final Integer done) {
        this.done = done;
    }
    public Integer getDone() {
        return this.done;
    }

	// cancelled
    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
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

	// reference
    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

	// validatedStartDate
    public void setValidatedStartDate(final Date validatedStartDate) {
        this.validatedStartDate = validatedStartDate;
    }
    public Date getValidatedStartDate() {
        return this.validatedStartDate;
    }

	// idActivityType
    public void setIdActivityType(final Integer idActivityType) {
        this.idActivityType = idActivityType;
    }
    public Integer getIdActivityType() {
        return this.idActivityType;
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

	// idPaymentDelay
    public void setIdPaymentDelay(final Integer idPaymentDelay) {
        this.idPaymentDelay = idPaymentDelay;
    }
    public Integer getIdPaymentDelay() {
        return this.idPaymentDelay;
    }

	// tax
    public void setTax(final BigDecimal tax) {
        this.tax = tax;
    }
    public BigDecimal getTax() {
        return this.tax;
    }

	// fullAmount
    public void setFullAmount(final BigDecimal fullAmount) {
        this.fullAmount = fullAmount;
    }
    public BigDecimal getFullAmount() {
        return this.fullAmount;
    }

	// addFullAmount
    public void setAddFullAmount(final BigDecimal addFullAmount) {
        this.addFullAmount = addFullAmount;
    }
    public BigDecimal getAddFullAmount() {
        return this.addFullAmount;
    }

	// totalFullAmount
    public void setTotalFullAmount(final BigDecimal totalFullAmount) {
        this.totalFullAmount = totalFullAmount;
    }
    public BigDecimal getTotalFullAmount() {
        return this.totalFullAmount;
    }

	// idDeliveryMode
    public void setIdDeliveryMode(final Integer idDeliveryMode) {
        this.idDeliveryMode = idDeliveryMode;
    }
    public Integer getIdDeliveryMode() {
        return this.idDeliveryMode;
    }

	// receptionDate
    public void setReceptionDate(final Date receptionDate) {
        this.receptionDate = receptionDate;
    }
    public Date getReceptionDate() {
        return this.receptionDate;
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
        sb.append(idCommandType);
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
        sb.append(additionalInfo);
        sb.append("|");
        sb.append(externalReference);
        sb.append("|");
        sb.append(idActivity);
        sb.append("|");
        sb.append(initialStartDate);
        sb.append("|");
        sb.append(initialEndDate);
        sb.append("|");
        sb.append(validatedEndDate);
        sb.append("|");
        sb.append(initialWork);
        sb.append("|");
        sb.append(initialPricePerDayAmount);
        sb.append("|");
        sb.append(untaxedAmount);
        sb.append("|");
        sb.append(addWork);
        sb.append("|");
        sb.append(addPricePerDayAmount);
        sb.append("|");
        sb.append(addUntaxedAmount);
        sb.append("|");
        sb.append(validatedWork);
        sb.append("|");
        sb.append(validatedPricePerDayAmount);
        sb.append("|");
        sb.append(totalUntaxedAmount);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(done);
        sb.append("|");
        sb.append(cancelled);
        sb.append("|");
        sb.append(idleDate);
        sb.append("|");
        sb.append(doneDate);
        sb.append("|");
        sb.append(handled);
        sb.append("|");
        sb.append(handledDate);
        sb.append("|");
        sb.append(reference);
        sb.append("|");
        sb.append(validatedStartDate);
        sb.append("|");
        sb.append(idActivityType);
        sb.append("|");
        sb.append(idClient);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(idPaymentDelay);
        sb.append("|");
        sb.append(tax);
        sb.append("|");
        sb.append(fullAmount);
        sb.append("|");
        sb.append(addFullAmount);
        sb.append("|");
        sb.append(totalFullAmount);
        sb.append("|");
        sb.append(idDeliveryMode);
        sb.append("|");
        sb.append(receptionDate);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

    
    // Attachments.
    private List<Attachments> attachments;
    public void setAttachments(final List<Attachments> attachments) {
        this.attachments = attachments;
    }
    public List<Attachments> getAttachments() {
        return this.attachments;
    }

}
