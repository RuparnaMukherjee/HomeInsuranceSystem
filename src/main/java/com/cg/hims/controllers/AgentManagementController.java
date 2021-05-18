package com.cg.hims.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.service.IAgentServiceImpl;

/*Controller Class for Agent Management
Author : Anudeep Biswas
Date Created : 9/5/2021
*/


@RestController
@RequestMapping("/agentManagement")
public class AgentManagementController{
	
	@Autowired
	 IAgentServiceImpl serviceobj;
	
	static final Logger LOGGER = LoggerFactory.getLogger(AgentManagementController.class);

/***************************************************************************************/
	// Create Agent
	@PostMapping("/agentCreation")
	public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
		if(agent.getAgentId()==0)
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		Agent agentImpl=serviceobj.addAgent(agent);
		LOGGER.info("Agent Added!!!");
		return new ResponseEntity<Agent>(agentImpl,HttpStatus.OK);
	}

/***************************************************************************************/
	// Get Particular Agent Data
	@GetMapping("/findAgent/{id}")
	public ResponseEntity<Optional<Agent>> findAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		Optional<Agent> findagent= serviceobj.findAgentById(id);
		if(findagent==null)
			return new ResponseEntity("No Agent Found for given Id",HttpStatus.NOT_FOUND);
		return new ResponseEntity(findagent,HttpStatus.OK);
	}

/***************************************************************************************/
	// All Agents List
	@GetMapping("/ViewAllAgents")
	public ResponseEntity<List<Agent>> viewAllAgents() {
		List<Agent> agentImpl=serviceobj.viewAllAgents();
		if(agentImpl.isEmpty())
			return new ResponseEntity("No Agents Found",HttpStatus.NOT_FOUND);
		return new ResponseEntity(agentImpl,HttpStatus.OK);
	}
	
/***************************************************************************************/
	// Updating Agent Data
	@PutMapping("/agentUpdate")
	public ResponseEntity<Agent> UpdateEmployee(@RequestBody Agent agent) throws AgentNotFoundException {
		if(agent==null)
			return new ResponseEntity("Enter Proper Agent Details",HttpStatus.NOT_FOUND);
		return new ResponseEntity(serviceobj.updateAgent(agent),HttpStatus.OK);
	}
	
/***************************************************************************************/
	// Delete Agent
	@DeleteMapping("/agentRemove/{id}")
	public String removeAgent(@PathVariable("id") int id) throws AgentNotFoundException {
		
		return serviceobj.removeAgent(id);
	}
	

}
