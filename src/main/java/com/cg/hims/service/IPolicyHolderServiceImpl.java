package com.cg.hims.service;
import java.util.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
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
    
    @Override
    public PolicyHolder addPolicyHolder(PolicyHolder policyHolder)
    {
    	holderdao.save(policyHolder);
    	return policyHolder;
    }
    
    @Override
    public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(policyHolder.getPolicyHolderId()))
    			throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	
    	holderdao.save(policyHolder);
    	return policyHolder;
    	
    }
    
    @Override
    public Optional<PolicyHolder> findPolicyHolderById(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
    		throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	return holderdao.findById(id);
    }
    
    @Override
    public String removePolicyHolder(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
			throw new PolicyHolderNotFoundException("quote not found");
		holderdao.deleteById(id);
		return "Policy Holder deleted successfully";
    }
    
    @Override
    public List<PolicyHolder> showAllPolicyHolders()
    {
    	return holderdao.findAll();
    	//return h_list;
    }
    public List<PolicyHolder> viewPolicyHolderByAgentId(int id) throws AgentNotFoundException {
		// TODO Auto-generated method stub
    	if(!agentDao.existsById(id))
    		throw new AgentNotFoundException("agent not found");
    	Optional<Agent> agent=agentDao.findById(id);
		return holderdao.findAllByAgent(agent);
	}
    public List<PolicyHolder> viewIdlePolicyHolder(){
    	return holderdao.viewIdlePolicyHolder();
    }
    
}
