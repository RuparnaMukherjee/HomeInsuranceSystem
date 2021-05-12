package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;

public interface IPolicyService {

	public Policy addPolicy(Policy policy);

	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException;

	public Optional<Policy> findPolicyById(String policyId) throws PolicyNotFoundException;

	public String removePolicy(String policyId) throws PolicyNotFoundException;

	public List<Policy> showAllPolicies();

}
