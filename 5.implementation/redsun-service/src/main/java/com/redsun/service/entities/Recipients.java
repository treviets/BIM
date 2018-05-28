package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Recipients entity
 */
@Component
@Scope("prototype")
public class Recipients extends AbstractEntity {

    // name
    private String name;
    // company_number
    private String companyNumber;
    // num_tax
    private String numTax;
    // bank_name
    private String bankName;
    // iban_country
    private String ibanCountry;
    // iban_key
    private String ibanKey;
    // iban_bban
    private String ibanBban;
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
    // tax_free
	private Integer taxFree;
    // idle
	private Integer idle;
    // legal_notice
    private String legalNotice;
    // contact_name
    private String contactName;
    // contact_email
    private String contactEmail;
    // contact_phone
    private String contactPhone;
    // contact_mobile
    private String contactMobile;
    // bank_national_account_number
    private String bankNationalAccountNumber;
    // bank_international_account_number
    private String bankInternationalAccountNumber;
    // bank_identification_code
    private String bankIdentificationCode;
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

	// companyNumber
    public void setCompanyNumber(final String companyNumber) {
        this.companyNumber = companyNumber;
    }
    public String getCompanyNumber() {
        return this.companyNumber;
    }

	// numTax
    public void setNumTax(final String numTax) {
        this.numTax = numTax;
    }
    public String getNumTax() {
        return this.numTax;
    }

	// bankName
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }
    public String getBankName() {
        return this.bankName;
    }

	// ibanCountry
    public void setIbanCountry(final String ibanCountry) {
        this.ibanCountry = ibanCountry;
    }
    public String getIbanCountry() {
        return this.ibanCountry;
    }

	// ibanKey
    public void setIbanKey(final String ibanKey) {
        this.ibanKey = ibanKey;
    }
    public String getIbanKey() {
        return this.ibanKey;
    }

	// ibanBban
    public void setIbanBban(final String ibanBban) {
        this.ibanBban = ibanBban;
    }
    public String getIbanBban() {
        return this.ibanBban;
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

	// taxFree
    public void setTaxFree(final Integer taxFree) {
        this.taxFree = taxFree;
    }
    public Integer getTaxFree() {
        return this.taxFree;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// legalNotice
    public void setLegalNotice(final String legalNotice) {
        this.legalNotice = legalNotice;
    }
    public String getLegalNotice() {
        return this.legalNotice;
    }

	// contactName
    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }
    public String getContactName() {
        return this.contactName;
    }

	// contactEmail
    public void setContactEmail(final String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getContactEmail() {
        return this.contactEmail;
    }

	// contactPhone
    public void setContactPhone(final String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactPhone() {
        return this.contactPhone;
    }

	// contactMobile
    public void setContactMobile(final String contactMobile) {
        this.contactMobile = contactMobile;
    }
    public String getContactMobile() {
        return this.contactMobile;
    }

	// bankNationalAccountNumber
    public void setBankNationalAccountNumber(final String bankNationalAccountNumber) {
        this.bankNationalAccountNumber = bankNationalAccountNumber;
    }
    public String getBankNationalAccountNumber() {
        return this.bankNationalAccountNumber;
    }

	// bankInternationalAccountNumber
    public void setBankInternationalAccountNumber(final String bankInternationalAccountNumber) {
        this.bankInternationalAccountNumber = bankInternationalAccountNumber;
    }
    public String getBankInternationalAccountNumber() {
        return this.bankInternationalAccountNumber;
    }

	// bankIdentificationCode
    public void setBankIdentificationCode(final String bankIdentificationCode) {
        this.bankIdentificationCode = bankIdentificationCode;
    }
    public String getBankIdentificationCode() {
        return this.bankIdentificationCode;
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
        sb.append(companyNumber);
        sb.append("|");
        sb.append(numTax);
        sb.append("|");
        sb.append(bankName);
        sb.append("|");
        sb.append(ibanCountry);
        sb.append("|");
        sb.append(ibanKey);
        sb.append("|");
        sb.append(ibanBban);
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
        sb.append(taxFree);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(legalNotice);
        sb.append("|");
        sb.append(contactName);
        sb.append("|");
        sb.append(contactEmail);
        sb.append("|");
        sb.append(contactPhone);
        sb.append("|");
        sb.append(contactMobile);
        sb.append("|");
        sb.append(bankNationalAccountNumber);
        sb.append("|");
        sb.append(bankInternationalAccountNumber);
        sb.append("|");
        sb.append(bankIdentificationCode);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
