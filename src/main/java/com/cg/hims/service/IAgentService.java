package com.cg.hims.service;

import java.util.List;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;

public interface IAgentService {

	public Agent addAgent(Agent agent);

	public Agent updateAgent(Agent agent) throws AgentNotFoundException;

	public String removeAgent(int agentId) throws AgentNotFoundException;

	public java.util.Optional findAgentById(int agentId) throws AgentNotFoundException;

	public List<Agent> viewAllAgents();
	//public List<PolicyHolder> viewPolicyHolder();

}
