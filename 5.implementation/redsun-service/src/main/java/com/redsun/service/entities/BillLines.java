package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BillLines entity
 */
@Component
@Scope("prototype")
public class BillLines extends AbstractEntity {

    // line
    private Integer line;
    // quantity
    private BigDecimal quantity;
    // description
    private String description;
    // detail
    private String detail;
    // price
    private BigDecimal price;
    // amount
    private BigDecimal amount;
    // ref_type
    private String refType;
    // ref_id
    private Integer refId;
    // id_term
    private Integer idTerm;
    // id_resource
    private Integer idResource;
    // id_activity_price
    private Integer idActivityPrice;
    // start_date
    private Date startDate;
    // end_date
    private Date endDate;
    // id_measure_unit
    private Integer idMeasureUnit;
    // extra
	private Integer extra;
    // billing_type
    private String billingType;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// line
    public void setLine(final Integer line) {
        this.line = line;
    }
    public Integer getLine() {
        return this.line;
    }

	// quantity
    public void setQuantity(final BigDecimal quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getQuantity() {
        return this.quantity;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// detail
    public void setDetail(final String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return this.detail;
    }

	// price
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

	// amount
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return this.amount;
    }

	// refType
    public void setRefType(final String refType) {
        this.refType = refType;
    }
    public String getRefType() {
        return this.refType;
    }

	// refId
    public void setRefId(final Integer refId) {
        this.refId = refId;
    }
    public Integer getRefId() {
        return this.refId;
    }

	// idTerm
    public void setIdTerm(final Integer idTerm) {
        this.idTerm = idTerm;
    }
    public Integer getIdTerm() {
        return this.idTerm;
    }

	// idResource
    public void setIdResource(final Integer idResource) {
        this.idResource = idResource;
    }
    public Integer getIdResource() {
        return this.idResource;
    }

	// idActivityPrice
    public void setIdActivityPrice(final Integer idActivityPrice) {
        this.idActivityPrice = idActivityPrice;
    }
    public Integer getIdActivityPrice() {
        return this.idActivityPrice;
    }

	// startDate
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }

	// endDate
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }

	// idMeasureUnit
    public void setIdMeasureUnit(final Integer idMeasureUnit) {
        this.idMeasureUnit = idMeasureUnit;
    }
    public Integer getIdMeasureUnit() {
        return this.idMeasureUnit;
    }

	// extra
    public void setExtra(final Integer extra) {
        this.extra = extra;
    }
    public Integer getExtra() {
        return this.extra;
    }

	// billingType
    public void setBillingType(final String billingType) {
        this.billingType = billingType;
    }
    public String getBillingType() {
        return this.billingType;
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
        sb.append(line);
        sb.append("|");
        sb.append(quantity);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(detail);
        sb.append("|");
        sb.append(price);
        sb.append("|");
        sb.append(amount);
        sb.append("|");
        sb.append(refType);
        sb.append("|");
        sb.append(refId);
        sb.append("|");
        sb.append(idTerm);
        sb.append("|");
        sb.append(idResource);
        sb.append("|");
        sb.append(idActivityPrice);
        sb.append("|");
        sb.append(startDate);
        sb.append("|");
        sb.append(endDate);
        sb.append("|");
        sb.append(idMeasureUnit);
        sb.append("|");
        sb.append(extra);
        sb.append("|");
        sb.append(billingType);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
