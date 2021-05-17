package com.cg.hims;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.hims.entities.Agent;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.service.IAgentServiceImpl;

/*Agent Management Test
Author : Anudeep Biswas
Date Created : 16/05/2021*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgentManagementTest {
	
	@InjectMocks
	private IAgentServiceImpl serviceobj;
	@Mock
	private IAgentRepository agent_repo;
	
	// 1. addAgent() Service Layer Check
	@Test
	public void addAgentTest() {
		
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Mockito.when(agent_repo.save(agent)).thenReturn(agent);
		
		assertEquals(agent, serviceobj.addAgent(agent));
		
	}
	
	// 2. viewAllAgents() Service Layer Check
	@Test
	 public void viewAllAgentsTest() {
		
		Agent agent1=new Agent();
		agent1.setAgentId(1);
		agent1.setAgentName("Samuel Eto");
		agent1.setDesignation("Sales");
		agent1.setSalary("10000");
		agent1.setAddress("Delhi");
		agent1.setEmail("sam1234@gmail.com");
		agent1.setMobileNo("7894561523");
		
		Agent agent2=new Agent();
		agent2.setAgentId(2);
		agent2.setAgentName("Thierry Henry");
		agent2.setDesignation("Sales");
		agent2.setSalary("15000");
		agent2.setAddress("Pune");
		agent2.setEmail("thenry@gmail.com");
		agent2.setMobileNo("9904578459");
		
		List<Agent> list=new ArrayList<Agent>();
		list.add(agent1);
		list.add(agent2);
			
		Mockito.when(agent_repo.findAll()).thenReturn(list);
		
		assertEquals(list,serviceobj.viewAllAgents());
	}

	// 3. updateAgent() Service Layer Check for Valid Input
	@Test
	public void testPositiveUpdatePolicy() throws AgentNotFoundException  {
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Thierry Henry");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Mockito.when(agent_repo.save(agent)).thenReturn(agent);
		
		Mockito.when(agent_repo.existsById(1)).thenReturn(true);
		agent.setAgentName("Samuel Eto");
		
		Agent testAgent=serviceobj.updateAgent(agent);
		
		assertEquals(agent.getAgentName(),testAgent.getAgentName());
	}
	
	// 4. updateAgent() Service Layer Check for Valid Input
	@Test 
	public void testNegativeUpdatePolicy() throws AgentNotFoundException {
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");

		Executable ex=()->{
			serviceobj.updateAgent(agent);
		};
		
		verify(agent_repo,never()).save(agent);
		Assertions.assertThrows(AgentNotFoundException.class,ex);
	}
	
	// 5. removeAgent() Service Layer Check for Valid Input
	@Test
	public void testPositiveDelete() throws AgentNotFoundException  {
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Mockito.when(agent_repo.save(agent)).thenReturn(agent);
		Mockito.when(agent_repo.existsById(1)).thenReturn(true);
		
		serviceobj.removeAgent(1);
		
		verify(agent_repo,Mockito.atLeastOnce()).deleteById(1);
		
	}
	
	// 6. removeAgent() Service Layer Check for Invalid Input
	@Test 
	public void testNegativeDelete()throws AgentNotFoundException{
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Mockito.when(agent_repo.existsById(2)).thenReturn(false);
		Executable ex=()->{
			serviceobj.removeAgent(2);
		};
		
		verify(agent_repo,never()).deleteById(1);
		Assertions.assertThrows(AgentNotFoundException.class,ex);
	}
	
	// 7. findAgentById() Service Layer Check for Valid Input
	@Test
	public void testPositivefindAgentById() throws AgentNotFoundException {
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Mockito.when(agent_repo.existsById(1)).thenReturn(true);
		Mockito.when(agent_repo.findById(1)).thenReturn(Optional.of(agent));

		assertEquals(Optional.of(agent),serviceobj.findAgentById(1));
	}

	// 8. findAgentById() Service Layer Check for Invalid Input
	@Test
	public void testNegativefindAgentById() throws AgentNotFoundException {
		Agent agent=new Agent();
		agent.setAgentId(1);
		agent.setAgentName("Samuel Eto");
		agent.setDesignation("Sales");
		agent.setSalary("10000");
		agent.setAddress("Delhi");
		agent.setEmail("sam1234@gmail.com");
		agent.setMobileNo("7894561523");
		
		Executable ex=()->{
			serviceobj.findAgentById(1);
		};
		
		verify(agent_repo,never()).findById(1);		
		Assertions.assertThrows(AgentNotFoundException.class, ex);
	}
	
	}
