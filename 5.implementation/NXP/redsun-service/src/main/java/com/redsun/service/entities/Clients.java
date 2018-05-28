package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Clients entity
 */
@Component
@Scope("prototype")
public class Clients extends AbstractEntity {

	// name
	private String name;
	// description
	private String description;
	// client_code
	private String clientCode;
	// idle
	private int idle;
	// payment_delay
	private int paymentDelay;
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
	// id_client_type
	private int idClientType;
	// payment_delay_end_of_month
	private int paymentDelayEndOfMonth;
	// num_tax
	private String numTax;
	// id_payment_delay
	private int idPaymentDelay;
	// client_id
	private int clientId;
	// totalCount
	private int totalCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public int getIdle() {
		return idle;
	}

	public void setIdle(int idle) {
		this.idle = idle;
	}

	public int getPaymentDelay() {
		return paymentDelay;
	}

	public void setPaymentDelay(int paymentDelay) {
		this.paymentDelay = paymentDelay;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getIdClientType() {
		return idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
	}

	public int getPaymentDelayEndOfMonth() {
		return paymentDelayEndOfMonth;
	}

	public void setPaymentDelayEndOfMonth(int paymentDelayEndOfMonth) {
		this.paymentDelayEndOfMonth = paymentDelayEndOfMonth;
	}

	public String getNumTax() {
		return numTax;
	}

	public void setNumTax(String numTax) {
		this.numTax = numTax;
	}

	public int getIdPaymentDelay() {
		return idPaymentDelay;
	}

	public void setIdPaymentDelay(int idPaymentDelay) {
		this.idPaymentDelay = idPaymentDelay;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
