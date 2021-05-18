package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.repository.IPolicyRepository;

/*
 * Author : KRITI DAS
 * Date : 16-05-2021
 * Description : This is Policy Service Implementation
*/

@Service
@Transactional
public class IPolicyServiceImpl implements IPolicyService{
	
	@Autowired
	IPolicyRepository policyDao;
	@Autowired
	IAgentRepository agentDao;
	
	/****************************
	* Method: addPolicy
	* Description: It is used to add Policy into Policy table
	* @Override: It is used to override the JpaRepository methods for performing CURD operations.
	* Created By- KRITI DAS
	* Created Date -16-05-2021
	 ****************************/

	@Override
	public Policy addPolicy(Policy policy) {
		// TODO Auto-generated method stub
		policyDao.save(policy);
		return policy;
	}

	/****************************
	 * Method: updatePolicy
	 * Description: It is used to update Policies into Policy table
	 * @return Policy: It returns Policy with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By- KRITI DAS
     *Created Date -16-05-2021
	 ****************************/
	
	@Override
	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policy.getPolicyId()))
			throw new PolicyNotFoundException("policy not found");
		policyDao.save(policy);
		return policy;
	}
	
	/****************************
	 * Method: findPolicyById 
	 * Description: It is used to view Policy by PolicyId from Policy table
	 * @return PolicyDao.findById(id): It returns Policy with given ID.
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By- KRITI DAS
     * Created Date -16-05-2021 
	 ****************************/

	@Override
	public Optional<Policy> findPolicyById(int policyId) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policyId))
			throw new PolicyNotFoundException("policy not found");
		return policyDao.findById(policyId);
	}
	
	/****************************
	 * Method: removePolicy 
	 * Description: It is used to remove Policy from Policy table
	 * @return : Returns String.
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-KRITI DAS
     * Created Date 16-05-2021 
	 ****************************/

	@Override
	public String removePolicy(int policyId) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		if(!policyDao.existsById(policyId))
			throw new PolicyNotFoundException("policy not found");
		policyDao.deleteById(policyId);
		return "Policy Deleted Successfully";
	}
	
	/****************************
	 * Method: showAllPolicies
	 * Description: It is used to view all Policies in Policy table
	 * @return policyList: It returns the list of policies.
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By- KRITI DAS
     * Created Date -16-05-2021
	 ****************************/

	@Override
	public List<Policy> showAllPolicies() {
		// TODO Auto-generated method stub
		List<Policy> policy=policyDao.findAll();
		return policy;
	}
	
	/****************************
	 * Method: viewPolicyByAgentId
	 * Description: It is used to view all Policies in Policy table under particular agent
	 * @return policyList: It returns the list of policies.
	 * Created By- KRITI DAS
     * Created Date -16-05-2021
	 ****************************/

	public List<Policy> viewPolicyByAgentId(int id) throws AgentNotFoundException {
		// TODO Auto-generated method stub
    	if(!agentDao.existsById(id))
    		throw new AgentNotFoundException("agent not found");
    	Optional<Agent> agent=agentDao.findById(id);
		return policyDao.findAllByAgent(agent);
	}

}
