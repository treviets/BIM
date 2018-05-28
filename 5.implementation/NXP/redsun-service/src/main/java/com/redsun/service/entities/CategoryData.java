package com.redsun.service.entities;

public class CategoryData {
	private int vendorStatusCount;
	private int statusID;
	private String statusName;
	private double transactionAmount;
	private String transactionCurrencyCode;
	/**
	 * @return the vendorStatusCount
	 */
	public int getVendorStatusCount() {
		return vendorStatusCount;
	}
	/**
	 * @param vendorStatusCount the vendorStatusCount to set
	 */
	public void setVendorStatusCount(int vendorStatusCount) {
		this.vendorStatusCount = vendorStatusCount;
	}
	/**
	 * @return the statusID
	 */
	public int getStatusID() {
		return statusID;
	}
	/**
	 * @param statusID the statusID to set
	 */
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	/**
	 * @return the transactionCurrencyCode
	 */
	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}
	/**
	 * @param transactionCurrencyCode the transactionCurrencyCode to set
	 */
	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}
	
	

}
