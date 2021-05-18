package com.cg.hims;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.repository.IPolicyHolderRepository;
import com.cg.hims.service.IPolicyHolderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyHolderTest {
	@Autowired
	private IPolicyHolderServiceImpl p_service;
	@MockBean
	private IPolicyHolderRepository holder;
	
	@Test
	public void addPolicyHolderTest()
	{
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(10000);
		Mockito.when(holder.save(obj)).thenReturn(obj);
		assertEquals(obj,p_service.addPolicyHolder(obj));
	}
	@Test
	public void showAllPolicyHolders()
	{
		PolicyHolder obj1=new PolicyHolder();
		PolicyHolder obj2=new PolicyHolder();
		obj1.setPolicyHolderId(1);
		obj2.setPolicyHolderId(2);
		obj1.setAnnualIncome(100000);
		obj2.setAnnualIncome(200000);
		Mockito.when(holder.findAll()).thenReturn(Stream.of(obj1,obj2).collect(Collectors.toList()));
		assertEquals(2,p_service.showAllPolicyHolders().size());
	}
	@Test
	public void removePolicyHolderTest1() throws PolicyHolderNotFoundException  {
		
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(1000);
		Executable executable = () -> {
			p_service.removePolicyHolder(obj.getPolicyHolderId());
		};
		verify(holder, never()).save(obj);
		Assertions.assertThrows(PolicyHolderNotFoundException.class, executable);
	}
	
	@Test
	public void removePolicyHolderTest2() throws PolicyHolderNotFoundException  {
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(1000);		
		Mockito.when(holder.existsById(1)).thenReturn(true);
		p_service.removePolicyHolder(1);
		verify(holder,Mockito.atLeastOnce()).deleteById(1);
	  }
	
	@Test
	public void updatePolicyHolderTest1() throws PolicyHolderNotFoundException {
		
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(12000);
		Executable executable = () -> {
			p_service.updatePolicyHolder(obj);
		};
		verify(holder, never()).save(obj);
		Assertions.assertThrows(PolicyHolderNotFoundException.class, executable);
		
	}
	
	@Test
	public void updatePolicyHolderTest2() throws PolicyHolderNotFoundException {
		
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(1000);
		Mockito.when(holder.save(obj)).thenReturn(obj);
		Mockito.when(holder.existsById(1)).thenReturn(true);
		PolicyHolder obj1=p_service.updatePolicyHolder(obj);
		assertEquals(obj,obj1);
	}
	
	@Test
	public void findPolicyHolderByIdTest1() throws PolicyHolderNotFoundException {
		
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(100000);
		Executable ex=()->{
			p_service.findPolicyHolderById(2);
		};
		verify(holder,never()).findById(1);
		Assertions.assertThrows(PolicyHolderNotFoundException.class, ex);
	}
	
	@Test
	public void findPolicyHolderByIdTest2() throws PolicyHolderNotFoundException {
		
		PolicyHolder obj=new PolicyHolder();
		obj.setPolicyHolderId(1);
		obj.setAnnualIncome(10000);
		Mockito.when(holder.existsById(1)).thenReturn(true);
		Mockito.when(holder.findById(1)).thenReturn(Optional.of(obj));
		
		assertEquals(obj,p_service.findPolicyHolderById(1).get());
	}
}
