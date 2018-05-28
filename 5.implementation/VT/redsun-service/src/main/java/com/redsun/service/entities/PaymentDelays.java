package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * PaymentDelays entity
 */
@Component
@Scope("prototype")
public class PaymentDelays extends AbstractEntity {

    // name
    private String name;
    // days
    private Integer days;
    // end_of_month
	private Integer endOfMonth;
    // sort_order
    private Integer sortOrder;
    // idle
	private Integer idle;
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

	// days
    public void setDays(final Integer days) {
        this.days = days;
    }
    public Integer getDays() {
        return this.days;
    }

	// endOfMonth
    public void setEndOfMonth(final Integer endOfMonth) {
        this.endOfMonth = endOfMonth;
    }
    public Integer getEndOfMonth() {
        return this.endOfMonth;
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
        sb.append(days);
        sb.append("|");
        sb.append(endOfMonth);
        sb.append("|");
        sb.append(sortOrder);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
