package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.repository.IPolicyRepository;

@Service
@Transactional
public class IPolicyServiceImpl implements IPolicyService{

	@Autowired
	IPolicyRepository policyDao;
	@Override
	public Policy addPolicy(Policy policy) {
		// TODO Auto-generated method stub
		policyDao.save(policy);
		return policy;
	}

	@Override
	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policy.getPolicyId()))
			throw new PolicyNotFoundException();
		policyDao.save(policy);
		return policy;
	}

	@Override
	public Optional<Policy> findPolicyById(int id) throws PolicyNotFoundException {
		if(!policyDao.existsById(id))
			throw new PolicyNotFoundException();
		return policyDao.findById(id);
	}

	@Override
	public String removePolicy(int id) throws PolicyNotFoundException {
		if(!policyDao.existsById(id))
			throw new PolicyNotFoundException();
		policyDao.deleteById(id);
		return "Policy deleted successfully";
	}

	@Override
	public List<Policy> showAllPolicies() {
		List<Policy> policyList=policyDao.findAll();
		return policyList;
	}

}
