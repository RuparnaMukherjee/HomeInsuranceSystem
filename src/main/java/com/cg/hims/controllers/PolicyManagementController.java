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
@RequestMapping("/quote")
public class PolicyManagementController {
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	
	@GetMapping("/Check")
	public String check() {
		return "checked";
	}
	
	
	@PostMapping("/CreatePolicy")
	public Policy addPolicy(@RequestBody Policy policy) {
		System.out.println("Policy Created");
		return PolicyImpl.addPolicy(policy);
	}
	
	
	@GetMapping("/ViewAllPolicy")
	public List<Policy> showAllPolicy() {
		return policyImpl.showAllPolicy();
	}
	
	
	@GetMapping("/ViewPolicyById/{id}")
	public Optional<Policy> findPolicyById(@PathVariable("id") int id) throws PolicyNotFoundException {
		return PolicyImpl.findPolicyById(id);
	}
	
	
	@PutMapping("/UpdatePolicy")
	public Policy updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException{
		return PolicyImpl.updatePolicy(policy);
	}
	
	
	@DeleteMapping("/DeletePolicy/{id}")
	public String removePolicy(@PathVariable("id") int id) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		return PolicyImpl.removePolicy(id);
	}

}