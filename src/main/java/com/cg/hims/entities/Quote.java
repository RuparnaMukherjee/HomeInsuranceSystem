package com.cg.hims.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Quote {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int quoteId;
	private String premiumType;
	private double premium;
	private double dwellingCoverage;
	private double detachedStructureCoverage;
	private double personalPropertyCoverage;
	private double additionalLivingExpense;
	private double medicalExpense;
	private double deductibleAmount;
	@OneToOne
	@JoinColumn(name="policy_id")
	private Policy policy;
	@OneToOne
	@JoinColumn(name="property_id")
	private Property property;
	public Quote()
	{}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public double getDwellingCoverage() {
		return dwellingCoverage;
	}
	public void setDwellingCoverage(double dwellingCoverage) {
		this.dwellingCoverage = dwellingCoverage;
	}
	public double getDetachedStructureCoverage() {
		return detachedStructureCoverage;
	}
	public void setDetachedStructureCoverage(double detachedStructureCoverage) {
		this.detachedStructureCoverage = detachedStructureCoverage;
	}
	public double getPersonalPropertyCoverage() {
		return personalPropertyCoverage;
	}
	public void setPersonalPropertyCoverage(double personalPropertyCoverage) {
		this.personalPropertyCoverage = personalPropertyCoverage;
	}
	public double getAdditionalLivingExpense() {
		return additionalLivingExpense;
	}
	public void setAdditionalLivingExpense(double additionalLivingExpense) {
		this.additionalLivingExpense = additionalLivingExpense;
	}
	public double getMedicalExpense() {
		return medicalExpense;
	}
	public void setMedicalExpense(double medicalExpense) {
		this.medicalExpense = medicalExpense;
	}
	public double getDeductibleAmount() {
		return deductibleAmount;
	}
	public void setDeductibleAmount(double deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	
}
