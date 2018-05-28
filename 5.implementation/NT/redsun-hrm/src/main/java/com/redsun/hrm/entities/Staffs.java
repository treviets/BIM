package com.redsun.hrm.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Resources entity
 */
@Component
@Scope("prototype")
public class Staffs extends AbstractEntity {
	private String code;
	private String title;
	private String idCard;
	private Date idCardIssueDate;
	private String idCareIssued;
	private String name;
	private String fullName;
	private int gender;
	private String dateOfBirth;
	private String placeOfBirth;
	private int religion;
	private int married;
	private String email;
	private String description;
	private String phone;
	private String mobile;
	private String fax;
	private String address;
	private String street;
	private String zip;
	private String city;
	private String state;
	private String country;
	private double salary;
	private String img;
	private int isUser;
	private int isResource;
	private int isContact;
	private int isTrash;
	private int clientId;
	private int deparmentId;
	private int positionId;
	private int totalCount;
	private Departments department;
	private Positions position;
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

	public Date getIdCardIssueDate() {
		return idCardIssueDate;
	}

	public void setIdCardIssueDate(Date idCardIssueDate) {
		this.idCardIssueDate = idCardIssueDate;
	}

	public String getIdCareIssued() {
		return idCareIssued;
	}

	public void setIdCareIssued(String idCareIssued) {
		this.idCareIssued = idCareIssued;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public int getReligion() {
		return religion;
	}

	public void setReligion(int religion) {
		this.religion = religion;
	}

	public int getMarried() {
		return married;
	}

	public void setMarried(int married) {
		this.married = married;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getDeparmentId() {
		return deparmentId;
	}

	public void setDeparmentId(int deparmentId) {
		this.deparmentId = deparmentId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Positions getPosition() {
		return position;
	}

	public void setPosition(Positions position) {
		this.position = position;
	}

}
