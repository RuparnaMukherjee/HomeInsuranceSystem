package com.cg.hims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Policy;
import com.cg.hims.service.IPolicyServiceImpl;

@RestController
@RequestMapping("/policy")
public class PolicyManagementController {

	@Autowired
	IPolicyServiceImpl policyImpl;
	
	//Show All policies 
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy(){
		List<Policy> policy=policyImpl.showAllPolicies();
		if(policy.isEmpty())
			return new ResponseEntity("No policy found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Policy>>(policy,HttpStatus.OK);
	}
}
