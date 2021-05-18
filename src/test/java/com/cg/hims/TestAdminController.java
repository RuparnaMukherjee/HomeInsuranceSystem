package com.cg.hims;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.service.IAgentServiceImpl;
import com.cg.hims.service.IPolicyHolderServiceImpl;
import com.cg.hims.service.IPolicyServiceImpl;
import com.cg.hims.service.IPropertyServiceImpl;
import com.cg.hims.service.IQuoteServiceImpl;
import com.cg.hims.service.IUserServiceImpl;
import com.cg.hims.user.controller.AdminController;



@RunWith(SpringRunner.class)			
@WebMvcTest(value = AdminController.class)
public class TestAdminController {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	 IPolicyHolderServiceImpl holderImpl;
	 @MockBean
	 IAgentServiceImpl agentImpl;
	 @MockBean
	 IPolicyServiceImpl policyImpl;
	 @MockBean
	 IPropertyServiceImpl propertImpl;
	 @MockBean
	 IQuoteServiceImpl quoteImpl;
	 @MockBean
	 IUserServiceImpl userImpl;
	 
	 @Test
	 public void testAddPolicyHolder() throws Exception {
		 PolicyHolder policyHolder=new PolicyHolder();
		 policyHolder.setPolicyHolderId(1);
		 policyHolder.setPolicyHolderName("Ruchan");
		 Mockito.when(holderImpl.addPolicyHolder(policyHolder)).thenReturn(policyHolder);
		 
		 RequestBuilder reqBuilder= MockMvcRequestBuilders
	    		 .post("/AdminDashboard/CreatePolicyHolder")
	    		 .accept(MediaType.APPLICATION_JSON).content(asJsonString(policyHolder))			//accepts JSON
	    		 .contentType(MediaType.APPLICATION_JSON);		
		 MvcResult result = mockMvc.perform(reqBuilder).andReturn();
	     MockHttpServletResponse response = result.getResponse();
	     
	     String outputInJson = response.getContentAsString();
	     assertThat(outputInJson).isEqualTo(asJsonString(policyHolder));
	     assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void testShowAllPolicyHolders() throws Exception {
		 PolicyHolder policyHolder=new PolicyHolder();
		 policyHolder.setPolicyHolderId(1);
		 policyHolder.setPolicyHolderName("Ruchan");
		 PolicyHolder policyHolder1=new PolicyHolder();
		 policyHolder1.setPolicyHolderId(2);
		 policyHolder1.setPolicyHolderName("Ruchan");
		 List<PolicyHolder> holderList=new ArrayList<>();
		 holderList.add(policyHolder);
		 holderList.add(policyHolder1);
		 Mockito.when(holderImpl.showAllPolicyHolders()).thenReturn(holderList);
		 RequestBuilder reqBuilder= MockMvcRequestBuilders
	    		 .get("/AdminDashboard/ViewAllPolicyHolders")
	    		 .accept(MediaType.APPLICATION_JSON).content(asJsonString(holderList))
	    		 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(reqBuilder).andReturn();
	     MockHttpServletResponse response = result.getResponse();
	     
	     String outputInJson = response.getContentAsString();
	     assertThat(outputInJson).isEqualTo(asJsonString(holderList));
	     assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 
	 @Test
	 public void testNegativeSearchPolicyHolderById()throws Exception{
		 
		 Mockito.when(holderImpl.findPolicyHolderById(Mockito.anyInt())).thenThrow(new PolicyHolderNotFoundException("not found"));
		 
		 RequestBuilder reqBuilder= MockMvcRequestBuilders
	    		 .get("/AdminDashboard/ViewPolicyHolderById/"+3)
	    		 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(reqBuilder).andReturn();
	     MockHttpServletResponse response = result.getResponse();
	    
	     assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		 
	 }
	 
	 @Test
	 public void testPositiveSearchPolicyHolderById()throws Exception{
		 PolicyHolder policyHolder=new PolicyHolder();
		 policyHolder.setPolicyHolderId(1);
		 
		 Mockito.when(holderImpl.findPolicyHolderById(1)).thenReturn(Optional.of(policyHolder));
		 
		 RequestBuilder reqBuilder= MockMvcRequestBuilders
	    		 .get("/AdminDashboard/ViewPolicyHolderById/"+1)
	    		 .accept(MediaType.APPLICATION_JSON).content(asJsonString(policyHolder))
	    		 .contentType(MediaType.APPLICATION_JSON);
		 
		 
	     MvcResult result = mockMvc.perform(reqBuilder).andReturn();
	     MockHttpServletResponse response = result.getResponse();
	     
	     String outputInJson = response.getContentAsString();
	     assertThat(outputInJson).isEqualTo(asJsonString(policyHolder));
	     assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 
	 @Test
	 public void testIdlePolicyHolder()throws Exception{
		 
	 }
	 
	 public static String asJsonString(final Object obj) {
	        try {
	        	ObjectMapper objectMapper = new ObjectMapper();
	  	      return objectMapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

}
