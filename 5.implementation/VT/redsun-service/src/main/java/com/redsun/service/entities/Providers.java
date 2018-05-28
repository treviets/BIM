package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Providers entity
 */
@Component
@Scope("prototype")
public class Providers extends AbstractEntity {

    // name
    private String name;
    // id_provider_type
    private Integer idProviderType;
    // description
    private String description;
    // provider_code
    private String providerCode;
    // id_payment_delay
    private Integer idPaymentDelay;
    // num_tax
    private String numTax;
    // tax
    private BigDecimal tax;
    // designation
    private String designation;
    // street
    private String street;
    // complement
    private String complement;
    // zip
    private String zip;
    // city
    private String city;
    // state
    private String state;
    // country
    private String country;
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

	// idProviderType
    public void setIdProviderType(final Integer idProviderType) {
        this.idProviderType = idProviderType;
    }
    public Integer getIdProviderType() {
        return this.idProviderType;
    }

	// description
    public void setDescription(final String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

	// providerCode
    public void setProviderCode(final String providerCode) {
        this.providerCode = providerCode;
    }
    public String getProviderCode() {
        return this.providerCode;
    }

	// idPaymentDelay
    public void setIdPaymentDelay(final Integer idPaymentDelay) {
        this.idPaymentDelay = idPaymentDelay;
    }
    public Integer getIdPaymentDelay() {
        return this.idPaymentDelay;
    }

	// numTax
    public void setNumTax(final String numTax) {
        this.numTax = numTax;
    }
    public String getNumTax() {
        return this.numTax;
    }

	// tax
    public void setTax(final BigDecimal tax) {
        this.tax = tax;
    }
    public BigDecimal getTax() {
        return this.tax;
    }

	// designation
    public void setDesignation(final String designation) {
        this.designation = designation;
    }
    public String getDesignation() {
        return this.designation;
    }

	// street
    public void setStreet(final String street) {
        this.street = street;
    }
    public String getStreet() {
        return this.street;
    }

	// complement
    public void setComplement(final String complement) {
        this.complement = complement;
    }
    public String getComplement() {
        return this.complement;
    }

	// zip
    public void setZip(final String zip) {
        this.zip = zip;
    }
    public String getZip() {
        return this.zip;
    }

	// city
    public void setCity(final String city) {
        this.city = city;
    }
    public String getCity() {
        return this.city;
    }

	// state
    public void setState(final String state) {
        this.state = state;
    }
    public String getState() {
        return this.state;
    }

	// country
    public void setCountry(final String country) {
        this.country = country;
    }
    public String getCountry() {
        return this.country;
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
        sb.append(idProviderType);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(providerCode);
        sb.append("|");
        sb.append(idPaymentDelay);
        sb.append("|");
        sb.append(numTax);
        sb.append("|");
        sb.append(tax);
        sb.append("|");
        sb.append(designation);
        sb.append("|");
        sb.append(street);
        sb.append("|");
        sb.append(complement);
        sb.append("|");
        sb.append(zip);
        sb.append("|");
        sb.append(city);
        sb.append("|");
        sb.append(state);
        sb.append("|");
        sb.append(country);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
