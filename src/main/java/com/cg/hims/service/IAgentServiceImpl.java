package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.repository.IAgentRepository;

@Service
@Transactional
public class IAgentServiceImpl implements IAgentService{
	
	@Autowired
	IAgentRepository agent_repo;

	@Override
	public Agent addAgent(Agent agent) {
		Agent agent_info= agent_repo.save(agent);
		return agent_info;
	}

	@Override
	public Agent updateAgent(Agent agent) throws AgentNotFoundException {
		if(!agent_repo.existsById(agent.getAgentId())) {
			throw new AgentNotFoundException("Oops!!No Agent found for given Id");
		}
		else {
			return agent_repo.save(agent);
		}
			
	}

	@Override
	public String removeAgent(int agentId) throws AgentNotFoundException {
		if(!agent_repo.existsById(agentId)) {
			throw new AgentNotFoundException("Oops!!No Agent found for given Id");
		}
		else {
			agent_repo.deleteById(agentId);
			return "Agent Deleted Successfully";
		}
	}

	@Override
	public Optional findAgentById(int agentId) throws AgentNotFoundException {
		if(!agent_repo.existsById(agentId)) {
			throw new AgentNotFoundException("Oops!!No Agent found for given Id");
		}
		else {
			return agent_repo.findById(agentId);
		}
	}

	@Override
	public List<Agent> viewAllAgents() {
		List<Agent> temp= agent_repo.findAll();
		return temp;
	}

	//@Override
//	public List<PolicyHolder> viewPolicyHolder() {
//		// TODO Auto-generated method stub
//		return agent_repo.viewPolicyHolder();
//	}

	
}
