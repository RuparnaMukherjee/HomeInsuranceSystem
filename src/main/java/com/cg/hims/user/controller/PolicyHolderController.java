package com.cg.hims.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cg.hims.service.IAgentServiceImpl;
import com.cg.hims.service.IPolicyHolderServiceImpl;
import com.cg.hims.service.IPolicyServiceImpl;

@RestController
@RequestMapping("/policyholderDashboard")
public class PolicyHolderController {
     
	@Autowired
	IPolicyHolderServiceImpl holderimpl;
	@Autowired
	 IAgentServiceImpl serviceobj;
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	@PostMapping("/AddPolicyholderdetails")
	public ResponseEntity<PolicyHolder> addQuote(@RequestBody PolicyHolder holder) {
		if(holder.getPolicyHolderId()==0)
			return new ResponseEntity("No Policy Holder",HttpStatus.NOT_FOUND);
		System.out.println("policy Holder added");
		PolicyHolder holder1= holderimpl.addPolicyHolder(holder);
		return new ResponseEntity <PolicyHolder>(holder ,HttpStatus.OK);
	}

	@GetMapping("/ViewPolicyHolderByEmailId/{id}")
	public Optional<PolicyHolder> findPolicyHolderById(@PathVariable("id") String id) throws PolicyHolderNotFoundException {
		return holderimpl.findPolicyHolderByEmailId(id);
	}
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy(){
		List<Policy> policy=policyImpl.showAllPolicies();
		if(policy.isEmpty())
			return new ResponseEntity("No policy found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Policy>>(policy,HttpStatus.OK);
	}
	@GetMapping("/ViewAllAgents")
	public List<Agent> viewAllAgents() {
		return serviceobj.viewAllAgents();
	}
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder holder) throws PolicyHolderNotFoundException{
		if(holder==null)
			return new ResponseEntity("enter the Policy Holder to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderimpl.updatePolicyHolder(holder),HttpStatus.OK);
	}
	
}
