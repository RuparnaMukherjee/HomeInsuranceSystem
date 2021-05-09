package com.cg.hims.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class PolicyHolder {
	@Id
	private int policyHolderId;
	private String policyHolderName;
	private String policyName;
	private String premiumType;
	private String creditCard;
	private String dob;
	private String occupation;
	private double annualIncome;
	private String isRetired;
	private String ssn;
	private String emailId;
	@OneToOne
	@JoinColumn(name="address_id")
	private HomeAddress address;
	@OneToOne
	@JoinColumn(name="policy_id")
	private Policy policy;
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	public PolicyHolder()
	{}
	public int getPolicyHolderId() {
		return policyHolderId;
	}
	public void setPolicyHolderId(int policyHolderId) {
		this.policyHolderId = policyHolderId;
	}
	public String getPolicyHolderName() {
		return policyHolderName;
	}
	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getIsRetired() {
		return isRetired;
	}
	public void setIsRetired(String isRetired) {
		this.isRetired = isRetired;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public HomeAddress getAddress() {
		return address;
	}
	public void setAddress(HomeAddress address) {
		this.address = address;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}
