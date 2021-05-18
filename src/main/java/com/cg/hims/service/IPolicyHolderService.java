package com.cg.hims.service;

import java.util.*;

import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;

//IPolicyHolderService interface
public interface IPolicyHolderService 
{
    //Abstract methods defined to perform CURD operation
	public PolicyHolder addPolicyHolder(PolicyHolder policyHolder);

	public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException;

	public Optional<PolicyHolder> findPolicyHolderById(int id) throws PolicyHolderNotFoundException;

	public String removePolicyHolder(int id) throws PolicyHolderNotFoundException;
	
	public Optional<PolicyHolder> findPolicyHolderByEmailId(String email) throws PolicyHolderNotFoundException;
	
	public List<PolicyHolder> showAllPolicyHolders();
}

