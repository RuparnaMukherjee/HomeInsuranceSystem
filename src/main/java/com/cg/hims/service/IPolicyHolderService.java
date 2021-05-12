package com.cg.hims.service;

import java.util.*;

import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;

public interface IPolicyHolderService {

	public PolicyHolder addPolicyHolder(PolicyHolder policyHolder);

	public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException;

	public Optional<PolicyHolder> findPolicyHolderById(int id) throws PolicyHolderNotFoundException;

	public String removePolicyHolder(int id) throws PolicyHolderNotFoundException;

	public List<PolicyHolder> showAllPolicyHolders();
	
	//public void makePayment();

}

