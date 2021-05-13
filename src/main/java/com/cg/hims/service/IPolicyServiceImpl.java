package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.repository.IPolicyRepository;

@Service
@Transactional
public class IPolicyServiceImpl implements IPolicyService{
	
	@Autowired
	IPolicyRepository policyDao;

	//add policy
	@Override
	public Policy addPolicy(Policy policy) {
		// TODO Auto-generated method stub
		policyDao.save(policy);
		return policy;
	}

	//update policy
	@Override
	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policy.getPolicyId()))
			throw new PolicyNotFoundException("policy not found");
		policyDao.save(policy);
		return policy;
	}

	
	//view policy by id
	@Override
	public Optional<Policy> findPolicyById(String policyId) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policyId))
			throw new PolicyNotFoundException("policy not found");
		return policyDao.findById(policyId);
	}

	//delete a policy
	@Override
	public String removePolicy(String policyId) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policyId))
			throw new PolicyNotFoundException("policy not found");
		policyDao.deleteById(policyId);
		return "Policy Deleted Successfully";
	}
	

	//Show all policies
	@Override
	public List<Policy> showAllPolicies() {
		// TODO Auto-generated method stub
		List<Policy> policy=policyDao.findAll();
		return policy;
	}

}
