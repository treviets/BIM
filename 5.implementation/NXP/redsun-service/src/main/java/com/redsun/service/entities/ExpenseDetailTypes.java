package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * ExpenseDetailTypes entity
 */
@Component
@Scope("prototype")
public class ExpenseDetailTypes extends AbstractEntity {

    private String name;
    private Integer sortOrder;
    private BigDecimal value01;
    private BigDecimal value02;
    private BigDecimal value03;
    private String unit01;
    private String unit02;
    private String unit03;
	private Integer idle;
    private String description;
	private Integer individual;
	private Integer project;
    private Integer clientId;
	private Integer totalCount;

    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setSortOrder(final Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setValue01(final BigDecimal value01) {
        this.value01 = value01;
    }
    public BigDecimal getValue01() {
        return this.value01;
    }

    public void setValue02(final BigDecimal value02) {
        this.value02 = value02;
    }
    public BigDecimal getValue02() {
        return this.value02;
    }

    public void setValue03(final BigDecimal value03) {
        this.value03 = value03;
    }
    public BigDecimal getValue03() {
        return this.value03;
    }

    public void setUnit01(final String unit01) {
        this.unit01 = unit01;
    }
    public String getUnit01() {
        return this.unit01;
    }

    public void setUnit02(final String unit02) {
        this.unit02 = unit02;
    }
    public String getUnit02() {
        return this.unit02;
    }

    public void setUnit03(final String unit03) {
        this.unit03 = unit03;
    }
    public String getUnit03() {
        return this.unit03;
    }

    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setIndividual(final Integer individual) {
        this.individual = individual;
    }
    public Integer getIndividual() {
        return this.individual;
    }

    public void setProject(final Integer project) {
        this.project = project;
    }
    public Integer getProject() {
        return this.project;
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
        sb.append(name);
        sb.append("|");
        sb.append(sortOrder);
        sb.append("|");
        sb.append(value01);
        sb.append("|");
        sb.append(value02);
        sb.append("|");
        sb.append(value03);
        sb.append("|");
        sb.append(unit01);
        sb.append("|");
        sb.append(unit02);
        sb.append("|");
        sb.append(unit03);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(individual);
        sb.append("|");
        sb.append(project);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
