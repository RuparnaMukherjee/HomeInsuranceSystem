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
import com.cg.hims.entities.Policy;

//RunWith annotation is used to run this test with SpringRunnerclass
@RunWith(SpringRunner.class)

//annotation works by creating the ApplicationContext used in your tests through SpringApplication.
@SpringBootTest 

public class TestPolicy {
	
	@Autowired
	private IPolicyService policyservice;
	
	//mocks the data from repository
	@MockBean 
	private IPolicyRepository policyrepository;
	
	//test case on adding policy to Database
	@Test
	public void addPolicyTest() {
		
		Policy policy=new Policy(101);
		
		//When repository saves data, then the policy object is returned.
		Mockito.when(policyrepository.save(policy)).thenReturn(policy);
		
	    //matches the expected policy object to the actual. 
		assertEquals(policy, policyservice.addPolicy(policy));
		
	}
	
	//test case to show all the policies from the database
	@Test
	 public void showAllPoliciesTest() {
		
		//if the findAll() method is true, case returns the below two values.
		Mockito.when(policyrepository.findAll())
		.thenReturn(Stream.of(new Policy(102),
				new Policy(102)).
				collect(Collectors.toList()));
		
		//expected number of data =2
		assertEquals(2,policyservice.showAllPolicies().size());
	}
	
	//test case to check if the policy is there (positive search)
	@Test
	public void testPositiveFindPolicyById() throws PolicyNotFoundException {
		Policy policy=new Policy();
		policy.setPolicyId(1); //setting a dummy id
		
		//if the id matches then return true
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);
		//else return an instance of Optional class
		Mockito.when(policyrepository.findById(1)).thenReturn(Optional.of(policy));
        
		//matches the policy ID
		assertEquals(policy.getPolicyId(),policyservice.findPolicyById(1).get().getPolicyId());
	}
    
	//test case for negative search.
	@Test
	public void testNegativeFindById() throws PolicyNotFoundException {
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		//The exception will be thrown to Executable if the search is negative.
		Executable ex=()->{
			policyservice.findPolicyById(2);
		};
		
		//verifies if the IDs match or not
		verify(policyrepository,never()).findById(1);
		
		//the exception is now thrown when verification is false.
		Assertions.assertThrows(PolicyNotFoundException.class, ex);
	}

	//test case for negative update
	@Test 
	public void testNegativeUpdatePolicy()throws PolicyNotFoundException {
		
		Policy policy=new Policy();
		policy.setPolicyId(1);

		Executable ex=()->{
			policyservice.updatePolicy(policy);
		};
		
		//verifies if the said data can be saved or not.
		verify(policyrepository,never()).save(policy);
		Assertions.assertThrows(PolicyNotFoundException.class,ex);
	}
    
	//test case for positive update
	@Test
	public void testPositiveUpdatePolicy()throws PolicyNotFoundException{
		
		Policy policy=new Policy();
		policy.setPolicyId(1);
        
		//returns the value after saving
		Mockito.when(policyrepository.save(policy)).thenReturn(policy);
		
		//if the ID exists then return True
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);
        
		assertEquals(policy,policyservice.updatePolicy(policy));
	}

	//test case for positive(successful) delete
	@Test 
	public void testPositiveDelete()throws PolicyNotFoundException{
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		//if the ID exists then remove the data
		Mockito.when(policyrepository.existsById(1)).thenReturn(true);
			policyservice.removePolicy(1);
			
		//Mockito.atLeastOnce()) checks if the delete method is invoked once	
		verify(policyrepository,Mockito.atLeastOnce()).deleteById(1);
	}

	//test case for negative delete
	@Test 
	public void testNegativeDelete()throws PolicyNotFoundException{
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		//checks if the IDs are same
		Mockito.when(policyrepository.existsById(2)).thenReturn(false);
		
		//exception is thrown
		Executable ex=()->{
			policyservice.removePolicy(2);
		};
		
		//verifies
		verify(policyrepository,never()).deleteById(1);
		Assertions.assertThrows(PolicyNotFoundException.class,ex);
	}


}