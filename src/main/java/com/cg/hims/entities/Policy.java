package com.cg.hims.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Policy {
	@Id
	private String policyId;
	private String policyEffectiveDate;
	private String policyEndDate;
	private int policyTerm;
	private String policyStatus;
	@OneToOne
	@JoinColumn(name="quote_id")
	private Quote quote;
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	@OneToOne
	@JoinColumn(name="policy_holder_id")
	private PolicyHolder policyHolder;
	public Policy()
	{}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}
	public void setPolicyEffectiveDate(String policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public int getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Quote getQuote() {
		return quote;
	}
	public void setQuoteId(Quote quote) {
		this.quote = quote;
	}
	

}
