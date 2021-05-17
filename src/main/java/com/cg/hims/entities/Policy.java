package com.cg.hims.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Policy {
	@Id
	private int policyId;
	private Date policyEffectiveDate;
	private Date policyEndDate;
	private int policyTerm;
	private String policyStatus;
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	public Policy()
	{}
	
	public Policy(int policyId) {
		super();
		this.policyId = policyId;
	}

	public Policy(int policyId, Date policyEffectiveDate, Date policyEndDate, int policyTerm, String policyStatus,
			Agent agent) {
		super();
		this.policyId = policyId;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyEndDate = policyEndDate;
		this.policyTerm = policyTerm;
		this.policyStatus = policyStatus;
		this.agent = agent;
	}

	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public Date getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}
	public void setPolicyEffectiveDate(Date policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}
	public Date getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(Date policyEndDate) {
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
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}
