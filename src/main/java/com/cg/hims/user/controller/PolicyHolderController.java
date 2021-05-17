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

/*
 * Author : RIDDHIMAN GHOSH ROY
 * Version : 1.0
 * Date : -0-2021
 * Description : This is PolicyHolder Controller
*/

@RestController
@RequestMapping("/policyholderDashboard")
public class PolicyHolderController {
     
	@Autowired
	IPolicyHolderServiceImpl holderimpl;
	@Autowired
	 IAgentServiceImpl serviceobj;
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	/************************************************************************************
	 * Method: addPolicyHolder
	 * Description: It is used to add PolicyHolder into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -  -0-2021 
	 * 
	 ************************************************************************************/
	
	@PostMapping("/AddPolicyholderdetails")
	public ResponseEntity<PolicyHolder> addQuote(@RequestBody PolicyHolder holder) 
	  {
		if(holder.getPolicyHolderId()==0)
			return new ResponseEntity("No Policy Holder",HttpStatus.NOT_FOUND);
		System.out.println("policy Holder added");
		PolicyHolder holder1= holderimpl.addPolicyHolder(holder);
		return new ResponseEntity <PolicyHolder>(holder ,HttpStatus.OK);
	  }
	
	/************************************************************************************
	 * Method: viewPolicyHolderByEmailId 
	 * Description: It is used to view PolicyHolder by emailId from PolicyHolder table
	 * @param PolicyHolder:String EmailId
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/ViewPolicyHolderByEmailId/{id}")
	public Optional<PolicyHolder> findPolicyHolderById(@PathVariable("id") String id) throws PolicyHolderNotFoundException {
		return holderimpl.findPolicyHolderByEmailId(id);
	}
	
	/************************************************************************************
	 * Method: viewAllPolicies 
	 * Description: It is used to view all Policies in Policy table
	 * @returns policy It returns policy with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ************************************************************************************/
	@GetMapping("/ViewAllPolicies")
	public ResponseEntity<List<Policy>> showAllPolicy()
	{
		List<Policy> policy=policyImpl.showAllPolicies();
		if(policy.isEmpty())
			return new ResponseEntity("No policy found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Policy>>(policy,HttpStatus.OK);
	}
	/************************************************************************************
	 * Method: viewAllAgents 
	 * Description: It is used to view all Agents in Agent table
	 * @returns Agent It returns agent with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ************************************************************************************/
	@GetMapping("/ViewAllAgents")
	public List<Agent> viewAllAgents() 
	{
		return serviceobj.viewAllAgents();
	}
	
	/************************************************************************************
	 * Method: updatePolicyHolder
	 * Description: It is used to update PolicyHolders into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ************************************************************************************/
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder holder) throws PolicyHolderNotFoundException
	{
		if(holder==null)
			return new ResponseEntity("enter the Policy Holder to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderimpl.updatePolicyHolder(holder),HttpStatus.OK);
	}
}
