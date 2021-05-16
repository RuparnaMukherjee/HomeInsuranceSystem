package com.cg.hims.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Policy implements Serializable {
	
	@Id
	private int policyId;
	private String policyEffectiveDate;
	private String policyEndDate;
	private int policyTerm;
	private String policyStatus;
		
	@ManyToOne
	@JoinColumn(name= "agent_id")
	private Agent agent;
	
	public Policy(int policyId, String policyEffectiveDate, String policyEndDate, int policyTerm, String policyStatus) {
		super();
		this.policyId = policyId;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyEndDate = policyEndDate;
		this.policyTerm = policyTerm;
		this.policyStatus = policyStatus;
		this.agent = agent;
		this.quote = quote;
		this.policyholder = policyholder;
	}
	@OneToOne
	@JoinColumn(name= "quote_id")
	private Quote quote;
	
	@OneToOne
	@JoinColumn(name= "policy_holder_id")
	private PolicyHolder policyholder;
	
	public Policy()
	{}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
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
	public Agent getAgent() {
		return agent;
	}
	public PolicyHolder getPolicyHolder()
	{
		return policyholder;
	}
	public void setQuoteId(Quote quote) {
		this.quote = quote;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public void setPolicyHolder(PolicyHolder policyholder) {
		this.policyholder = policyholder;
	}
	
	

}
