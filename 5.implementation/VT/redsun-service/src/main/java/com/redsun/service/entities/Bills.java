package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Bills entity
 */
@Component
@Scope("prototype")
public class Bills extends AbstractEntity {

    // id_bill_type
    private Integer idBillType;
    // name
    private String name;
    // id_project
    private Integer idProject;
    // id_client
    private Integer idClient;
    // id_contact
    private Integer idContact;
    // id_recipient
    private Integer idRecipient;
    // billing_type
    private String billingType;
    // description
    private String description;
    // date
    private Date date;
    // id_status
    private Integer idStatus;
    // done
	private Integer done;
    // idle
	private Integer idle;
    // bill_id
    private Integer billId;
    // tax
    private BigDecimal tax;
    // untaxed_amount
    private BigDecimal untaxedAmount;
    // full_amount
    private BigDecimal fullAmount;
    // cancelled
	private Integer cancelled;
    // id_activity_type
    private Integer idActivityType;
    // reference
    private String reference;
    // payment_done
	private Integer paymentDone;
    // payment_date
    private Date paymentDate;
    // payment_amount
    private BigDecimal paymentAmount;
    // id_payment_delay
    private Integer idPaymentDelay;
    // payment_due_date
    private Date paymentDueDate;
    // id_delivery_mode
    private Integer idDeliveryMode;
    // id_resource
    private Integer idResource;
    // id_user
    private Integer idUser;
    // creation_date
    private Date creationDate;
    // payments_count
    private Integer paymentsCount;
    // command_amount_pct
    private Integer commandAmountPct;
    // send_date
    private Date sendDate;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// idBillType
    public void setIdBillType(final Integer idBillType) {
        this.idBillType = idBillType;
    }
    public Integer getIdBillType() {
        return this.idBillType;
    }

	// name
    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

	// idProject
    public void setIdProject(final Integer idProject) {
        this.idProject = idProject;
    }
    public Integer getIdProject() {
        return this.idProject;
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

	// idRecipient
    public void setIdRecipient(final Integer idRecipient) {
        this.idRecipient = idRecipient;
    }
    public Integer getIdRecipient() {
        return this.idRecipient;
    }

	// billingType
    public void setBillingType(final String billingType) {
        this.billingType = billingType;
    }
    public String getBillingType() {
        return this.billingType;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// date
    public void setDate(final Date date) {
        this.date = date;
    }
    public Date getDate() {
        return this.date;
    }

	// idStatus
    public void setIdStatus(final Integer idStatus) {
        this.idStatus = idStatus;
    }
    public Integer getIdStatus() {
        return this.idStatus;
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

	// billId
    public void setBillId(final Integer billId) {
        this.billId = billId;
    }
    public Integer getBillId() {
        return this.billId;
    }

	// tax
    public void setTax(final BigDecimal tax) {
        this.tax = tax;
    }
    public BigDecimal getTax() {
        return this.tax;
    }

	// untaxedAmount
    public void setUntaxedAmount(final BigDecimal untaxedAmount) {
        this.untaxedAmount = untaxedAmount;
    }
    public BigDecimal getUntaxedAmount() {
        return this.untaxedAmount;
    }

	// fullAmount
    public void setFullAmount(final BigDecimal fullAmount) {
        this.fullAmount = fullAmount;
    }
    public BigDecimal getFullAmount() {
        return this.fullAmount;
    }

	// cancelled
    public void setCancelled(final Integer cancelled) {
        this.cancelled = cancelled;
    }
    public Integer getCancelled() {
        return this.cancelled;
    }

	// idActivityType
    public void setIdActivityType(final Integer idActivityType) {
        this.idActivityType = idActivityType;
    }
    public Integer getIdActivityType() {
        return this.idActivityType;
    }

	// reference
    public void setReference(final String reference) {
        this.reference = reference;
    }
    public String getReference() {
        return this.reference;
    }

	// paymentDone
    public void setPaymentDone(final Integer paymentDone) {
        this.paymentDone = paymentDone;
    }
    public Integer getPaymentDone() {
        return this.paymentDone;
    }

	// paymentDate
    public void setPaymentDate(final Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Date getPaymentDate() {
        return this.paymentDate;
    }

	// paymentAmount
    public void setPaymentAmount(final BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

	// idPaymentDelay
    public void setIdPaymentDelay(final Integer idPaymentDelay) {
        this.idPaymentDelay = idPaymentDelay;
    }
    public Integer getIdPaymentDelay() {
        return this.idPaymentDelay;
    }

	// paymentDueDate
    public void setPaymentDueDate(final Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }
    public Date getPaymentDueDate() {
        return this.paymentDueDate;
    }

	// idDeliveryMode
    public void setIdDeliveryMode(final Integer idDeliveryMode) {
        this.idDeliveryMode = idDeliveryMode;
    }
    public Integer getIdDeliveryMode() {
        return this.idDeliveryMode;
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

	// creationDate
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }

	// paymentsCount
    public void setPaymentsCount(final Integer paymentsCount) {
        this.paymentsCount = paymentsCount;
    }
    public Integer getPaymentsCount() {
        return this.paymentsCount;
    }

	// commandAmountPct
    public void setCommandAmountPct(final Integer commandAmountPct) {
        this.commandAmountPct = commandAmountPct;
    }
    public Integer getCommandAmountPct() {
        return this.commandAmountPct;
    }

	// sendDate
    public void setSendDate(final Date sendDate) {
        this.sendDate = sendDate;
    }
    public Date getSendDate() {
        return this.sendDate;
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
        sb.append(idBillType);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(idProject);
        sb.append("|");
        sb.append(idClient);
        sb.append("|");
        sb.append(idContact);
        sb.append("|");
        sb.append(idRecipient);
        sb.append("|");
        sb.append(billingType);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(date);
        sb.append("|");
        sb.append(idStatus);
        sb.append("|");
        sb.append(done);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(billId);
        sb.append("|");
        sb.append(tax);
        sb.append("|");
        sb.append(untaxedAmount);
        sb.append("|");
        sb.append(fullAmount);
        sb.append("|");
        sb.append(cancelled);
        sb.append("|");
        sb.append(idActivityType);
        sb.append("|");
        sb.append(reference);
        sb.append("|");
        sb.append(paymentDone);
        sb.append("|");
        sb.append(paymentDate);
        sb.append("|");
        sb.append(paymentAmount);
        sb.append("|");
        sb.append(idPaymentDelay);
        sb.append("|");
        sb.append(paymentDueDate);
        sb.append("|");
        sb.append(idDeliveryMode);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(paymentsCount);
        sb.append("|");
        sb.append(commandAmountPct);
        sb.append("|");
        sb.append(sendDate);
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
