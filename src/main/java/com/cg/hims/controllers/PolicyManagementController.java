package com.cg.hims.controllers;

import java.util.List;
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
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.service.IAgentServiceImpl;
import com.cg.hims.service.IPolicyServiceImpl;

@RestController
@RequestMapping("/policy")
public class PolicyManagementController {

	@Autowired
	IPolicyServiceImpl policyImpl;
	
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy(){
		List<Policy> policy=policyImpl.showAllPolicies();
		if(policy.isEmpty())
			return new ResponseEntity("No policy found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Policy>>(policy,HttpStatus.OK);
		}
		@PostMapping("/CreatePolicy") //We enter data into DB 
		public Policy addPolicy(@RequestBody Policy policy) { //RequestBody is used because we use raw data in Postman for input
			System.out.println("Policy Created");
			return policyImpl.addPolicy(policy);
		}
			
		@GetMapping("/ViewPolicyById/{id}") //{id} is wildcard which means we can use ViewPolicyById/100 to extract the policy with 100 from DB
		public Optional<Policy> findPolicyById(@PathVariable("id") int id) throws PolicyNotFoundException {
			return policyImpl.findPolicyById(id);
		}
		
		@PutMapping("/UpdatePolicy") //updates the policy from raw data entered
		public Policy updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException{
			return policyImpl.updatePolicy(policy);
		}
		
		@DeleteMapping("/DeletePolicy/{id}") //deletes the policy
		public String removePolicy(@PathVariable("id") int id) throws PolicyNotFoundException {
			
					return policyImpl.removePolicy(id);
		}
		
}
