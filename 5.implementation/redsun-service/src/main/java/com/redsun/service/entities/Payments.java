package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Payments entity
 */
@Component
@Scope("prototype")
public class Payments extends AbstractEntity {

    // name
    private String name;
    // id_bill
    private Integer idBill;
    // payment_date
    private Date paymentDate;
    // id_payment_mode
    private Integer idPaymentMode;
    // idle
	private Integer idle;
    // id_payment_type
    private Integer idPaymentType;
    // payment_amount
    private BigDecimal paymentAmount;
    // payment_fee_amount
    private BigDecimal paymentFeeAmount;
    // payment_credit_amount
    private BigDecimal paymentCreditAmount;
    // description
    private String description;
    // id_user
    private Integer idUser;
    // creation_date
    private Date creationDate;
    // reference_bill
    private String referenceBill;
    // id_client
    private Integer idClient;
    // id_recipient
    private Integer idRecipient;
    // bill_amount
    private BigDecimal billAmount;
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

	// idBill
    public void setIdBill(final Integer idBill) {
        this.idBill = idBill;
    }
    public Integer getIdBill() {
        return this.idBill;
    }

	// paymentDate
    public void setPaymentDate(final Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Date getPaymentDate() {
        return this.paymentDate;
    }

	// idPaymentMode
    public void setIdPaymentMode(final Integer idPaymentMode) {
        this.idPaymentMode = idPaymentMode;
    }
    public Integer getIdPaymentMode() {
        return this.idPaymentMode;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// idPaymentType
    public void setIdPaymentType(final Integer idPaymentType) {
        this.idPaymentType = idPaymentType;
    }
    public Integer getIdPaymentType() {
        return this.idPaymentType;
    }

	// paymentAmount
    public void setPaymentAmount(final BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

	// paymentFeeAmount
    public void setPaymentFeeAmount(final BigDecimal paymentFeeAmount) {
        this.paymentFeeAmount = paymentFeeAmount;
    }
    public BigDecimal getPaymentFeeAmount() {
        return this.paymentFeeAmount;
    }

	// paymentCreditAmount
    public void setPaymentCreditAmount(final BigDecimal paymentCreditAmount) {
        this.paymentCreditAmount = paymentCreditAmount;
    }
    public BigDecimal getPaymentCreditAmount() {
        return this.paymentCreditAmount;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
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

	// referenceBill
    public void setReferenceBill(final String referenceBill) {
        this.referenceBill = referenceBill;
    }
    public String getReferenceBill() {
        return this.referenceBill;
    }

	// idClient
    public void setIdClient(final Integer idClient) {
        this.idClient = idClient;
    }
    public Integer getIdClient() {
        return this.idClient;
    }

	// idRecipient
    public void setIdRecipient(final Integer idRecipient) {
        this.idRecipient = idRecipient;
    }
    public Integer getIdRecipient() {
        return this.idRecipient;
    }

	// billAmount
    public void setBillAmount(final BigDecimal billAmount) {
        this.billAmount = billAmount;
    }
    public BigDecimal getBillAmount() {
        return this.billAmount;
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
        sb.append(idBill);
        sb.append("|");
        sb.append(paymentDate);
        sb.append("|");
        sb.append(idPaymentMode);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(idPaymentType);
        sb.append("|");
        sb.append(paymentAmount);
        sb.append("|");
        sb.append(paymentFeeAmount);
        sb.append("|");
        sb.append(paymentCreditAmount);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(idUser);
        sb.append("|");
        sb.append(creationDate);
        sb.append("|");
        sb.append(referenceBill);
        sb.append("|");
        sb.append(idClient);
        sb.append("|");
        sb.append(idRecipient);
        sb.append("|");
        sb.append(billAmount);
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
