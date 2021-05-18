package com.cg.hims.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.service.IPolicyHolderServiceImpl;
import com.cg.hims.user.controller.AgentController;

/*
 * Author : RIDDHIMAN GHOSH ROY
 * Version : 1.0
 * Date : -0-2021
 * Description : This is PolicyHolder Controller
*/

@RestController
@RequestMapping("/policyholder")
public class PolicyHolderManagementController
{
	@Autowired
	IPolicyHolderServiceImpl holderimpl;
	
	static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);   //logger object
	
	/****************************
	 * Method: addPolicyHolder
	 * Description: It is used to add PolicyHolder into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -  -0-2021 
	 * 
	 ****************************/
	
	@PostMapping("/AddPolicyholder")
	public ResponseEntity<PolicyHolder> addPolicyHolder(@RequestBody PolicyHolder holder) 
	{
		if(holder.getPolicyHolderId()==0)
			return new ResponseEntity("No Policy Holder",HttpStatus.NOT_FOUND);
		LOGGER.info("policy Holder added");
		PolicyHolder holder1= holderimpl.addPolicyHolder(holder);
		return new ResponseEntity <PolicyHolder>(holder ,HttpStatus.OK);
	}
	
	/****************************
	 * Method: viewAllPolicyHolders 
	 * Description: It is used to view all PolicyHolders in PolicyHolder table
	 * @returns policy It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ****************************/
	
	@GetMapping("/ViewAllPolicyHolder")
	public ResponseEntity<List<PolicyHolder>> showAllpolicyHolders() 
	{
		List<PolicyHolder> h_list=holderimpl.showAllPolicyHolders();
		if(h_list.isEmpty())
			return new ResponseEntity("PolicyHolder not found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<PolicyHolder>>(h_list,HttpStatus.OK);
	}
	
	/****************************
	 * Method: viewPolicyHolderById 
	 * Description: It is used to view PolicyHolder by Id from PolicyHolder table
	 * @param PolicyHolder:int Id
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ****************************/
	
	@GetMapping("/ViewPolicyHolderById/{id}")
	public Optional<PolicyHolder> findPolicyHolderById(@PathVariable("id") int id) throws PolicyHolderNotFoundException 
	{
		return holderimpl.findPolicyHolderById(id);
	}
	
	/****************************
	 * Method: updatePolicyHolder
	 * Description: It is used to update PolicyHolders into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  -0-2021 
	 * 
	 ****************************/
	
	@PutMapping("/UpdatePolicyHolder")
	public ResponseEntity<PolicyHolder> updatePolicyHolder(@RequestBody PolicyHolder holder) throws PolicyHolderNotFoundException
	{
		if(holder==null)
			return new ResponseEntity("enter the Policy Holder to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<PolicyHolder>(holderimpl.updatePolicyHolder(holder),HttpStatus.OK);
	}
	
	/****************************
	 * Method: DeletePolicyHolder
	 * Description: It is used to remove PolicyHolder from PolicyHolder table
	 * @param PolicyHolder: int id
	 * @returns String It returns String type message.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -  04-04-2021 
	 * 
	 ****************************/
	
	@DeleteMapping("/DeletePolicyHolder/{id}")
	public String removePolicyHolder(@PathVariable("id") int id) throws PolicyHolderNotFoundException
	{
		// TODO Auto-generated method stub
		return holderimpl.removePolicyHolder(id);
	}

}