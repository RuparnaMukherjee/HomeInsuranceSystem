package com.cg.hims.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class HomeAddress {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int addressId;
	private String residenceType;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	@Column(unique=true)
	@Pattern(regexp="[0-9]{6}")
	private String zip;
	private String residenceUse;
	public HomeAddress()
	{}
	public String getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getResidenceUse() {
		return residenceUse;
	}
	public void setResidenceUse(String residenceUse) {
		this.residenceUse = residenceUse;
	}
	
	
}
