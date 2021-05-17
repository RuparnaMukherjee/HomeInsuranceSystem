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
 * Version:1.0
 * date created:13/05/2021
 */

@RestController
@RequestMapping("/AdminDashboard")
public class AdminController {

	/************************************************************************************
	 *Service Objects Autowired
	 ************************************************************************************/
	@Autowired
	IPolicyHolderServiceImpl holderImpl;
	@Autowired
	IAgentServiceImpl agentImpl;
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	/*
	 * Policy Holder Management
	 */
	
	/************************************************************************************
	 * Method: createPolicyHolder 
	 * Description: It is used to add policy holder into policy_holder table
	 * @param policyHolder: policy holder's reference variable.
	 * @returns policyHolder: It returns policy holder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 ************************************************************************************/
	
	@PostMapping("/CreatePolicyHolder")
	public ResponseEntity<PolicyHolder> createPolicyHolder(@RequestBody PolicyHolder policyHolder) {
		if (Objects.isNull(policyHolder)) {
			return new ResponseEntity("No Policy Holder Found", HttpStatus.NOT_FOUND);
		}
		PolicyHolder holder1 = holderImpl.addPolicyHolder(policyHolder);
		System.out.println("policy Holder added");
		return new ResponseEntity<PolicyHolder>(policyHolder, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewAllPolicyHolders 
	 * Description: It is used to view all policy holders in policy_holder table
	 * @returns List policyHolder: It returns all policy holder with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 ************************************************************************************/
	
	@GetMapping("/ViewAllPolicyHolders")
	public ResponseEntity<List<PolicyHolder>> viewAllPolicyHolders() {
		List<PolicyHolder> h_list = holderImpl.showAllPolicyHolders();
		if (h_list.isEmpty()) {
			return new ResponseEntity("PolicyHolder not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(h_list, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewIdlePolicyHolders 
	 * Description: It is used to list policy holders without agent into policy_holder table
	 * @returns List policyHolder: It returns list of policy holder with details
	 * @GetMapping: It is used to handle the HTTP Get requests matched with given
	 *               URI expression.
	 ************************************************************************************/
	
	@GetMapping("/ViewIdlePolicyHolders")
	public ResponseEntity<List<PolicyHolder>> viewIdlePolicyHolders() {
		List<PolicyHolder> h_list = holderImpl.viewIdlePolicyHolder();
		if (h_list.isEmpty()) {
			return new ResponseEntity("No policy holder present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(h_list, HttpStatus.OK);
	}
	
	/************************************************************************************
	 * Method: viewPolicyHolderById 
	 * Description: It is used to retrieve quote from quote
	 * 				table by checking the id
	 * @returns quote: It returns quote with details
	 * @GetMapping: annotated methods handle the HTTP GET requests matched with
	 *              given URI expression
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@GetMapping("/ViewPolicyHolderById/{id}")
	public Optional<PolicyHolder> viewPolicyHolderById(@PathVariable("id") int id)
			throws PolicyHolderNotFoundException {
		return holderImpl.findPolicyHolderById(id);
	}
	
	/************************************************************************************
	 * Method: updatePolicyHolder
	 * Description: It is used to update a policy holder record from
	 * 				policy_holder table
	 * @returns policy holder: It returns policy holder with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given
	 *              URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 ************************************************************************************/
	
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder policyHolder)
			throws PolicyHolderNotFoundException {
		if (policyHolder == null)
			return new ResponseEntity("enter the Policy Holder to be updated", HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderImpl.updatePolicyHolder(policyHolder), HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: deletePolicyHolder
	 * Description: It is used to delete a policy holder record from
	 * 				policy_holder table
	 * @returns String: It returns string value specifying successful deletion or
	 *         			failure of deletion
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with
	 *                 given URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@DeleteMapping("/DeletePolicyHolder/{id}")
	public String deletePolicyHolder(@PathVariable("id") int id) throws PolicyHolderNotFoundException {
		return holderImpl.removePolicyHolder(id);
	}

	/**************************************************************************************************************/

	/*
	 * Agent Management
	 */

	/************************************************************************************
	 * Method: addAgent 
	 * Description: It is used to add agent agent table
	 * @param agent: agent's reference variable.
	 * @returns agent: It returns agent with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 ************************************************************************************/
	
	@PostMapping("/CreateAgent")
	public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
		if (Objects.isNull(agent)) {
			return new ResponseEntity("No Agent Found", HttpStatus.NOT_FOUND);
		}
		Agent agent1 = agentImpl.addAgent(agent);
		System.out.println("Agent added");
		return new ResponseEntity<Agent>(agent, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewAllAgents 
	 * Description: It is used to view all agent in agent table
	 * @returns List agent: It returns list of agent with details
	 * @PGetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 ************************************************************************************/
	
	@GetMapping("/ViewAllAgents")
	public ResponseEntity<List<Agent>> viewAllAgents() {
		List<Agent> agentList = agentImpl.viewAllAgents();
		if (agentList.isEmpty()) {
			return new ResponseEntity("No policy holder present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Agent>>(agentList, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewAgentById
	 * Description: It is used to view all agent in agent table
	 * @returns  agent: It returns specific agent with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@GetMapping("/ViewAgentById/{id}")
	public Optional<Agent> viewAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		return agentImpl.findAgentById(id);
	}

	/************************************************************************************
	 * Method: viewAssignedPolicyHolders
	 * Description: It is used to view all policyholders assigned under specific agent id
	 * @returns List : It returns list of policy holders with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@GetMapping("/ViewPolicyHoldersByAgentId/{id}")
	public ResponseEntity<List<PolicyHolder>> viewAssignedPolicyHolders(@PathVariable("id") int id)
			throws AgentNotFoundException {
		List<PolicyHolder> holderList = holderImpl.viewPolicyHolderByAgentId(id);
		if (holderList.isEmpty()) {
			return new ResponseEntity("No policy holder present under this agent", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PolicyHolder>>(holderList, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: updateAgent 
	 * Description: It is used to update a agent record from
	 * 				agent table
	 * @returns agent: It returns agent with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given
	 *              URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 ************************************************************************************/
	
	@PutMapping("/UpdateAgent")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) throws AgentNotFoundException {
		if (agent == null)
			return new ResponseEntity("Agent not found to be updated", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Agent>(agentImpl.updateAgent(agent), HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: deleteAgentById
	 * Description: It is used to delete a agent record from
	 * 				agent table
	 * @returns String: It returns string value specifying successful deletion or
	 *         			failure of deletion
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with
	 *                 given URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@DeleteMapping("/DeleteAgentById/{id}")
	public String deleteAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		return agentImpl.removeAgent(id);
	}

	/***********************************************************************************************************/
	/*
	 * Policy Management
	 */

	/************************************************************************************
	 * Method: addPolicy
	 * Description: It is used to add policy in policy table
	 * @param policy: policy's reference variable.
	 * @returns agent: It returns agent with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 ************************************************************************************/
	
	@PostMapping("/CreatePolicy")
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		if (Objects.isNull(policy)) {
			return new ResponseEntity("No Policy Found", HttpStatus.NOT_FOUND);
		}
		Policy policy1 = policyImpl.addPolicy(policy);
		System.out.println("Policy added");
		return new ResponseEntity<Policy>(policy, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewAllPolicies
	 * Description: It is used to view all policy in policy table
	 * @returns List policy: It returns list of policy with details
	 * @PGetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 ************************************************************************************/
	
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy() {
		List<Policy> policyList = policyImpl.showAllPolicies();
		if (policyList.isEmpty()) {
			return new ResponseEntity("No policy holder present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Policy>>(policyList, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: viewPolicyById
	 * Description: It is used to view certain policy in policy table
	 * @returns  policy: It returns specific policy with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@GetMapping("/ViewPolicyById/{id}")
	public Optional<Policy> findPolicyById(@PathVariable("id") int id) throws PolicyNotFoundException {
		return policyImpl.findPolicyById(id);
	}

	/************************************************************************************
	 * Method: viewPolicyUnderAgent
	 * Description: It is used to view list of policies under particular agent id
	 * @returns List policy: It returns list of policy with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given
	 *               URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@GetMapping("/ViewPolicyUnderAgent/{id}")
	public List<Policy> viewPolicyUnderAgent(@PathVariable("id") int id) throws AgentNotFoundException {
		return policyImpl.viewPolicyByAgentId(id);
	}

	/************************************************************************************
	 * Method: updatePolicy
	 * Description: It is used to update a policy record from
	 * 				policy table
	 * @returns policy: It returns policy with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given
	 *              URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 ************************************************************************************/
	
	@PutMapping("/UpdatePolicy")
	public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException {
		if (policy == null)
			return new ResponseEntity("Policy not found to be updated", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Policy>(policyImpl.updatePolicy(policy), HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: deletePolicy
	 * Description: It is used to delete a policy record from
	 * 				policy table
	 * @returns String: It returns string value specifying successful deletion or
	 *         			failure of deletion
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with
	 *                 given URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/
	
	@DeleteMapping("/DeletePolicy/{id}")
	public String deletePolicy(@PathVariable("id") int id) throws PolicyNotFoundException {
		return policyImpl.removePolicy(id);
	}
}
