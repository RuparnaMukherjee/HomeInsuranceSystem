package com.cg.hims.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Agent {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int agentId;
	private String agentName;
	private String designation;
	private String salary; 
	private String address;
	@Column(unique=true)
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	@Column(unique=true)
	@Pattern(regexp="[0-9]{10}")
	private String mobileNo;
	@OneToMany(mappedBy="agent",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<PolicyHolder> policyHoldersList=new HashSet<>();
	@OneToMany(mappedBy="agent",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Policy> policies;
	public Agent() {
		
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Set<PolicyHolder> getPolicyHoldersList() {
		return policyHoldersList;
	}
	public void setPolicyHoldersList(HashSet<PolicyHolder> policyHoldersList) {
		this.policyHoldersList = policyHoldersList;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}


}
