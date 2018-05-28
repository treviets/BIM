package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Resources entity
 */
@Component
@Scope("prototype")
public class Resources extends AbstractEntity {

	// code
	private String code;
	// title
	private String title;
	// idCard
	private String idCard;
	// name
	private String name;
	// full_name
	private String fullName;
	// email
	private String email;
	// description
	private String description;
	// phone
	private String phone;
	// mobile
	private String mobile;
	// fax
	private String fax;
	// address
	private String address;
	// street
	private String street;
	// zip
	private String zip;
	// city
	private String city;
	// state
	private String state;
	// country
	private String country;
	// salary
	private double salary;
	// is_user
	private int isUser;
	// is_resource
	private int isResource;
	// is_contact
	private int isContact;
	// is_trash
	private int isTrash;
	// client_id
	private int clientId;
	// totalCount
	private int totalCount;

	public static String formatNumber(String num) {
		String chuyen = "" + num + "";
		char[] mang = chuyen.toCharArray();
		int count = 0;
		int count2 = 0;
		String kq = "";
		for (int i = mang.length - 1; i >= 0; i--) {
			count++;
			count2++;
			if ((count2 - mang.length) == 0) {
				kq += mang[i];
			} else if (count == 3 || count == 6) {

				kq += mang[i] + ".";
			} else {
				kq += mang[i];
			}
		}

		char[] mang2 = kq.toCharArray();
		kq = "";
		for (int i = mang2.length - 1; i >= 0; i--) {
			kq += mang2[i];
		}
		return kq;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getIsUser() {
		return isUser;
	}

	public void setIsUser(int isUser) {
		this.isUser = isUser;
	}

	public int getIsResource() {
		return isResource;
	}

	public void setIsResource(int isResource) {
		this.isResource = isResource;
	}

	public int getIsContact() {
		return isContact;
	}

	public void setIsContact(int isContact) {
		this.isContact = isContact;
	}

	public int getIsTrash() {
		return isTrash;
	}

	public void setIsTrash(int isTrash) {
		this.isTrash = isTrash;
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
