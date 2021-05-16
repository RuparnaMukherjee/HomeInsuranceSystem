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

import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.repository.IPolicyRepository;
import com.cg.hims.service.IPolicyService;
import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.entities.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPolicy {
	
	@Autowired
	private IPolicyService policyservice;
	
	@MockBean
	private IPolicyRepository policyrepository;
	
	@Test
	public void addPolicyTest() {
		
		Policy policy=new Policy();
		policy.setPolicyId(1);
				
		Mockito.when(policyrepository.save(policy)).thenReturn(policy);
		assertEquals(policy,policyservice.addPolicy(policy));		
	}
	
	@Test
	 public void showAllPoliciesTest() {
		Mockito.when(policyrepository.findAll())
		.thenReturn(Stream.of(new Policy(102, "03-March-2017", "03-November-2021", 4, "Ongoing"),
				new Policy(102, "08-December-2015", "03-December-2020", 5, "Paid")).
				collect(Collectors.toList()));
		assertEquals(2,policyservice.showAllPolicies().size());
	}
	
	@Test
	public void testPositiveFindPolicyById() throws PolicyNotFoundException {
		Policy policy=new Policy();
		policy.setPolicyId(1);
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);
		Mockito.when(policyrepository.findById(1)).thenReturn(Optional.of(policy));

		assertEquals(policy.getPolicyId(),policyservice.findPolicyById(1).get().getPolicyId());
	}

	@Test
	public void testNegativeFindById() throws PolicyNotFoundException {
		Policy policy=new Policy();
		policy.setPolicyId(1);
		Executable ex=()->{
			policyservice.findPolicyById(2);
		};
		verify(policyrepository,never()).findById(1);
		Assertions.assertThrows(PolicyNotFoundException.class, ex);
	}

	@Test 
	public void testNegativeUpdatePolicy()throws PolicyNotFoundException {
		
		Policy policy=new Policy();
		policy.setPolicyId(1);

		Executable ex=()->{
			policyservice.updatePolicy(policy);
		};
		verify(policyrepository,never()).save(policy);
		Assertions.assertThrows(PolicyNotFoundException.class,ex);
	}

	@Test
	public void testPositiveUpdatePolicy()throws PolicyNotFoundException{
		
		Policy policy=new Policy();
		policy.setPolicyId(1);

		Mockito.when(policyrepository.save(policy)).thenReturn(policy);
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);

		assertEquals(policy,policyservice.updatePolicy(policy));
	}

	@Test 
	public void testPositiveDelete()throws PolicyNotFoundException{
		Policy policy=new Policy();
		policy.setPolicyId(1);
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);
			policyservice.removePolicy(1);
		verify(policyrepository,Mockito.atLeastOnce()).deleteById(1);
	}

	@Test 
	public void testNegativeDelete()throws PolicyNotFoundException{
		Policy policy=new Policy();
		policy.setPolicyId(1);
		Mockito.when(policyrepository.existsById(2)).thenReturn(false);
		Executable ex=()->{
			policyservice.removePolicy(2);
		};
		verify(policyrepository,never()).deleteById(1);
		Assertions.assertThrows(PolicyNotFoundException.class,ex);
	}


}

