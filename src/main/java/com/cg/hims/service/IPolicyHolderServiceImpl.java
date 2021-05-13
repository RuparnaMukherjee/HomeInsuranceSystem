package com.cg.hims.service;
import java.util.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.repository.IPolicyHolderRepository;


@Service
@Transactional
public class IPolicyHolderServiceImpl implements IPolicyHolderService {
	     
    @Autowired 
	IPolicyHolderRepository holderdao;
    @Autowired
    IAgentRepository agentDao;
    
//add new policyholder
    @Override
    public PolicyHolder addPolicyHolder(PolicyHolder policyHolder)
    {
    	holderdao.save(policyHolder);
    	return policyHolder;
    }
    
    //update policy holder by id
    @Override
    public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(policyHolder.getPolicyHolderId()))
    			throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	
    	holderdao.save(policyHolder);
    	return policyHolder;
    	
    }
    
  //Retrieve policyholder by id	
    @Override
    public Optional<PolicyHolder> findPolicyHolderById(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
    		throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	return holderdao.findById(id);
    }
    
   //Delete a policyholder	
    @Override
    public String removePolicyHolder(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
			throw new PolicyHolderNotFoundException("quote not found");
		holderdao.deleteById(id);
		return "Quote deleted successfully";
    }
    
	
    //Show all policyholders	
    @Override
    public List<PolicyHolder> showAllPolicyHolders()
    {
    	return holderdao.findAll();
    	//return h_list;
    }
	
	//View policyholder by id
    public List<Object> viewPolicyHolder(int id) {
		// TODO Auto-generated method stub
    	Optional<Agent> agent=agentDao.findById(id);
		return holderdao.findAllByAgent(agent);
	}
    
}
