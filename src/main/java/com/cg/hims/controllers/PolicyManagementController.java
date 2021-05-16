package com.cg.hims.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping("/policy") //Request Mapping tells what URL will initiate what method
public class PolicyManagementController {
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	
	@GetMapping("/Check") //Shows the data available in table
	public String check() {
		return "checked";
	}
	
	
	@PostMapping("/CreatePolicy") //We enter data into DB 
	public Policy addPolicy(@RequestBody Policy policy) { //RequestBody is used because we use raw data in Postman for input
		System.out.println("Policy Created");
		return policyImpl.addPolicy(policy);
	}
	
	
	@GetMapping("/ViewAllPolicy") //Shows the data available in table
	public List<Policy> showAllPolicies() {
		return policyImpl.showAllPolicies();
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