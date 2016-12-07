package com.chinookDB.beans;

public class Customer {
	public Customer(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getSupportRepId() {
		return supportRepId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getCompany() {
		return company;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public String getFax() {
		return fax;
	}
	public String getEmail() {
		return email;
	}
	public void setSupportRepId(int supportRepId) {
		this.supportRepId = supportRepId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	int id;
	int supportRepId;
	String firstName;
	String lastName;
	String company;
	String address;
	String city;
	String state;
	String country;
	String postalCode;
	String phone;
	String fax;
	String email;
}
