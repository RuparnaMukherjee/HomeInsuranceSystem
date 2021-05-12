package com.cg.hims.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Agent;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.service.IAgentServiceImpl;

@RestController
@RequestMapping("/agent")
public class AgentManagementController{
	@Autowired
	 IAgentServiceImpl serviceobj;

	// Create Agent
	@PostMapping("/agentCreation")
	public Agent addAgent(@RequestBody Agent agent) {
		System.out.println("yes..Add Agent...");
		return serviceobj.addAgent(agent);
	}

	// Get Particular Agent Data
	@GetMapping("/findAgent/{id}")
	public Optional<Agent> findAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		return serviceobj.findAgentById(id);
	}

	// All Agents List
	@GetMapping("/ViewAllAgents")
	public List<Agent> viewAllAgents() {
		return serviceobj.viewAllAgents();
	}

	// Updating Agent Data
	@PutMapping("/agentUpdate")
	public Agent UpdateEmployee(@RequestBody Agent agent) throws AgentNotFoundException {
		return serviceobj.updateAgent(agent);

	}

	// Delete Agent
	@DeleteMapping("/agentRemove/{id}")
	public String removeAgent(@PathVariable("id") int id) throws AgentNotFoundException {
		return serviceobj.removeAgent(id);
	}

}

