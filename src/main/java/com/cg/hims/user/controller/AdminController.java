package com.cg.hims.user.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.service.IAgentServiceImpl;
import com.cg.hims.service.IPolicyHolderServiceImpl;
import com.cg.hims.service.IPolicyServiceImpl;

/*
 * Admin Controller
 * Author:Ruparna Mukherjee
 * Created at:13/05/2021
 */

@RestController
@RequestMapping("/AdminDashboard")
public class AdminController {
	
@GetMapping("/check")
public String check() {
	return "ok";
}

/**************************************************************************************/
/*
 * Service Objects Autowired Here
 */
	@Autowired
	IPolicyHolderServiceImpl holderImpl;
	@Autowired
	 IAgentServiceImpl agentImpl;
	@Autowired
	IPolicyServiceImpl policyImpl;
/**************************************************************************************/
/*
 * Policy Holder Management
 */
	//Create policy holder
	@PostMapping("/CreatePolicyHolder")
	public ResponseEntity<PolicyHolder> createPolicyHolder(@RequestBody PolicyHolder policyHolder)
	{
		if(Objects.isNull(policyHolder))
		{
			return new ResponseEntity("No Policy Holder Found",HttpStatus.NOT_FOUND);
		}
		PolicyHolder holder1= holderImpl.addPolicyHolder(policyHolder);
		System.out.println("policy Holder added");
		return new ResponseEntity <PolicyHolder>(policyHolder ,HttpStatus.OK);
	}
	
	//View all policy holders in database
	@GetMapping("/ViewAllPolicyHolders")
	public ResponseEntity<List<PolicyHolder>> viewAllPolicyHolders()
	{
		List<PolicyHolder> h_list=holderImpl.showAllPolicyHolders();
		if(h_list.isEmpty())
		{
			return new ResponseEntity("PolicyHolder not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(h_list,HttpStatus.OK);
	}
	
	//View policy holders without agent
	@GetMapping("/ViewIdlePolicyHolders")
	public ResponseEntity<List<PolicyHolder>> viewIdlePolicyHolders()
	{
		List<PolicyHolder> h_list=holderImpl.viewIdlePolicyHolder();
		if(h_list.isEmpty())
		{
			return new ResponseEntity("No policy holder present",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(h_list,HttpStatus.OK); 
	}
		
	//View Policy Holder by policy holder id
	@GetMapping("/ViewPolicyHolderById/{id}")
	public Optional<PolicyHolder> viewPolicyHolderById(@PathVariable("id") int id) throws PolicyHolderNotFoundException
	{
		return holderImpl.findPolicyHolderById(id);
	}
	
	//Update policyHolders
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder policyHolder) throws PolicyHolderNotFoundException {
		if(policyHolder==null)
			return new ResponseEntity("enter the Policy Holder to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderImpl.updatePolicyHolder(policyHolder),HttpStatus.OK);
	}
	
	//Delete policy holders
	@DeleteMapping("/DeletePolicyHolder/{id}")
	public String deletePolicyHolder(@PathVariable("id") int id) throws PolicyHolderNotFoundException {
		return holderImpl.removePolicyHolder(id);
	}
/**************************************************************************************/
	
/*
 * Agent Management
 */
	//Create new Agent
	@PostMapping("/CreateAgent")
	public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
		if(Objects.isNull(agent))
		{
			return new ResponseEntity("No Agent Found",HttpStatus.NOT_FOUND);
		}
		Agent agent1= agentImpl.addAgent(agent);
		System.out.println("Agent added");
		return new ResponseEntity<Agent>(agent ,HttpStatus.OK);
	}
	
	//View all agents
	@GetMapping("/ViewAllAgents")
	public ResponseEntity<List<Agent>> viewAllAgents(){
		List<Agent> agentList=agentImpl.viewAllAgents();
		if(agentList.isEmpty())
		{
			return new ResponseEntity("No policy holder present",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Agent>>(agentList,HttpStatus.OK); 
	}
	
	//View agent by id
	@GetMapping("/ViewAgentById/{id}")
	public Optional<Agent> viewAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		return agentImpl.findAgentById(id);
	}
	
	//View policy holders under specific agent
	@GetMapping("/ViewPolicyHoldersByAgentId/{id}")
	public ResponseEntity<List<PolicyHolder>> viewAssignedPolicyHolders(@PathVariable("id") int id) throws AgentNotFoundException
	{
		List<PolicyHolder> holderList=holderImpl.viewPolicyHolderByAgentId(id);
		if(holderList.isEmpty())
		{
			return new ResponseEntity("No policy holder present under this agent",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(holderList,HttpStatus.OK); 
	}
	
	//Update agent details
	@PutMapping("/UpdateAgent")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) throws AgentNotFoundException {
		if(agent==null)
			return new ResponseEntity("Agent not found to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Agent>(agentImpl.updateAgent(agent),HttpStatus.OK);
	}
	
	//Delete agent
	@DeleteMapping("/DeleteAgentById/{id}")
	public String deleteAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		return agentImpl.removeAgent(id);
	}

/**************************************************************************************/
/*
 * Policy Management
 */

	//Create a Policy
	@PostMapping("/CreatePolicy") 
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		if(Objects.isNull(policy))
		{
			return new ResponseEntity("No Policy Found",HttpStatus.NOT_FOUND);
		}
		Policy policy1= policyImpl.addPolicy(policy);
		System.out.println("Policy added");
		return new ResponseEntity<Policy>(policy ,HttpStatus.OK);
	}
	
	//View All Policy
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy(){
		List<Policy> policyList=policyImpl.showAllPolicies();
		if(policyList.isEmpty())
		{
			return new ResponseEntity("No policy holder present",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Policy>>(policyList,HttpStatus.OK); 
	}
	
	//View Policy by id
	@GetMapping("/ViewPolicyById/{id}")
	public Optional<Policy> findPolicyById(@PathVariable("id") int id) throws PolicyNotFoundException {
		return policyImpl.findPolicyById(id);
	}
	
	//View Policies under specific agent
	@GetMapping("/ViewPolicyUnderAgent/{id}")
	public List<Policy> viewPolicyUnderAgent(@PathVariable("id") int id) throws AgentNotFoundException
	{
		return policyImpl.viewPolicyByAgentId(id);
	}
	
	//Update Policy by id
	@PutMapping("/UpdatePolicy")
	public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException
	{
		if(policy==null)
			return new ResponseEntity("Policy not found to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Policy>(policyImpl.updatePolicy(policy),HttpStatus.OK);
	}
	
	//DeletePolicy by Id
	@DeleteMapping("/DeletePolicy/{id}")
	public String deletePolicy(@PathVariable("id") int id) throws PolicyNotFoundException {
		return policyImpl.removePolicy(id);
	}
}
