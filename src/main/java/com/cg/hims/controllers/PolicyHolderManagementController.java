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
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.service.IPolicyHolderServiceImpl;

@RestController
@RequestMapping("/policyholder")
public class PolicyHolderManagementController {
	@Autowired
	IPolicyHolderServiceImpl holderimpl;
	
	
	//Create new quote in database
	@PostMapping("/AddPolicyholder")
	public ResponseEntity<PolicyHolder> addQuote(@RequestBody PolicyHolder holder) {
		if(holder.getPolicyHolderId()==0)
			return new ResponseEntity("No Policy Holder",HttpStatus.NOT_FOUND);
		System.out.println("policy Holder added");
		PolicyHolder holder1= holderimpl.addPolicyHolder(holder);
		return new ResponseEntity <PolicyHolder>(holder ,HttpStatus.OK);
	}
	
	//Show all quotes from database
	@GetMapping("/ViewAllPolicyHolder")
	public ResponseEntity<List<PolicyHolder>> showAllpolicyHolders() {
		List<PolicyHolder> h_list=holderimpl.showAllPolicyHolders();
		if(h_list==null)
			return new ResponseEntity("PolicyHolder not found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<PolicyHolder>>(h_list,HttpStatus.OK);
	}
	
	//Show quote from id
	@GetMapping("/ViewPolicyHolderById/{id}")
	public Optional<PolicyHolder> findPolicyHolderById(@PathVariable("id") int id) throws PolicyHolderNotFoundException {
		return holderimpl.findPolicyHolderById(id);
	}
	
	//Update quote 
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder holder) throws PolicyHolderNotFoundException{
		if(holder==null)
			return new ResponseEntity("enter the Policy Holder to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderimpl.updatePolicyHolder(holder),HttpStatus.OK);
	}
	
	//Delete quote from database
	@DeleteMapping("/DeletePolicyHolder/{id}")
	public String removePolicyHolder(@PathVariable("id") int id) throws PolicyHolderNotFoundException {
		// TODO Auto-generated method stub
		return holderimpl.removePolicyHolder(id);
	}
	
	@GetMapping("/GetPolicyHolders/{id}")
	public List<PolicyHolder> getPolicyHolders(@PathVariable("id") int id) throws AgentNotFoundException{
		return holderimpl.viewPolicyHolderByAgentId(id);
	}

}