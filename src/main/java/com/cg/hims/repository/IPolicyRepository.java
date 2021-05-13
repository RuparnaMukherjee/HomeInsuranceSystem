package com.cg.hims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;

public interface IPolicyRepository extends JpaRepository<Policy,Integer> {

//	public Policy addPolicy(Policy policy);
//
//	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException;
//
//	public Policy findPolicyById(int policyId) throws PolicyNotFoundException;
//
//	public Policy removePolicy(int policyId) throws PolicyNotFoundException;
//
//	public List<Policy> showAllPolicies();
	@Query("Select p from Policy p  where p.agent=?1")
    List<Policy> findAllByAgent(Optional<Agent> agent);

}
