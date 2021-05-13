package com.cg.hims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.hims.entities.*;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;

@Repository
public interface IPolicyHolderRepository extends JpaRepository<PolicyHolder,Integer>{

	//public PolicyHolder addPolicyHolder(PolicyHolder policyHolder);

	//public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException;

	//public PolicyHolder findPolicyHolderById(int id) throws PolicyHolderNotFoundException;

	//public PolicyHolder removePolicyHolder(int id) throws PolicyHolderNotFoundException;

	//public  void makePayment();
		@Query("Select a.policyHolderId,a.policyHolderName from PolicyHolder a where a.agent=?1")
	    List<Object> findAllByAgent(Optional<Agent> agent);
	
}
