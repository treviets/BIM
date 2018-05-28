package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by HauLL on 2/10/2017.
 */
@Component
@Scope("prototype")
public class Term extends AbstractEntity {

    private String name;
    private String idProject;
    private Double amount;
    private Double validatedAmount;
    private Double plannedAmount;
    private LocalDateTime date;
    private LocalDateTime validatedDate;
    private LocalDateTime plannedDate;
    private Boolean idle;
    private String idBill;
    private String idUser;
    private LocalDateTime creationDate;


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(final String idProject) {
        this.idProject = idProject;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public Double getValidatedAmount() {
        return validatedAmount;
    }

    public void setValidatedAmount(final Double validatedAmount) {
        this.validatedAmount = validatedAmount;
    }

    public Double getPlannedAmount() {
        return plannedAmount;
    }

    public void setPlannedAmount(final Double plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getValidatedDate() {
        return validatedDate;
    }

    public void setValidatedDate(final LocalDateTime validatedDate) {
        this.validatedDate = validatedDate;
    }

    public LocalDateTime getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(final LocalDateTime plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Boolean getIdle() {
        return idle;
    }

    public void setIdle(final Boolean idle) {
        this.idle = idle;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(final String idBill) {
        this.idBill = idBill;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(final String idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
