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


import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.service.IPolicyServiceImpl;

/*Controller Class for Policy Management
Author : Kriti Das
Date Created : 9/5/2021
*/

@RestController

//Request Mapping tells what URL will initiate what method
@RequestMapping("/policy")

public class PolicyManagementController {
	
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	/*************************/
	
	//Check for Controller	
	@GetMapping("/Check") 
	public String check() {
		return "checked";
	}
	
	/*************************/
	
	//Create Policy	
	@PostMapping("/CreatePolicy")  
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		if(policy.getPolicyId()==0)
		return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		Policy policyimpl= policyImpl.addPolicy(policy);
		System.out.println("Policy Created");
		return new ResponseEntity(policyimpl, HttpStatus.OK);
	}
	
	/*************************/
	
	//View All Policy
	@GetMapping("/ViewAllPolicy")
	public ResponseEntity<List<Policy>> showAllPolicies() {
		List<Policy> policyimpl= policyImpl.showAllPolicies();
		if((policyimpl).isEmpty())
		return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		return new ResponseEntity(policyimpl, HttpStatus.OK);
	}
	
	/*************************/
	
	//View specific policy by ID
	
	//{id} is wildcard which means we can use ViewPolicyById/100 to extract the policy with 100 from DB
	@GetMapping("/ViewPolicyById/{id}") 
	
	public Optional<Policy> findPolicyById(@PathVariable("id") int id) throws PolicyNotFoundException {
		return policyImpl.findPolicyById(id);
	}
	
	/*************************/
	
	//Update Policy
	@PutMapping("/UpdatePolicy") 
	public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException{
		if(policy==null)
			return new ResponseEntity("Enter proper policy",HttpStatus.NOT_FOUND);	
		return new ResponseEntity(policyImpl.updatePolicy(policy),HttpStatus.OK);
	}
	
	/*************************/
	
	//Delete Policy
	@DeleteMapping("/DeletePolicy/{id}") 
	public String removePolicy(@PathVariable("id") int id) throws PolicyNotFoundException {
		
				return policyImpl.removePolicy(id);
	}

}