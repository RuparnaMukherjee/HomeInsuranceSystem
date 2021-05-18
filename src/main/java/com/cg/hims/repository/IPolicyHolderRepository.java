package com.cg.hims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
//PolicyHolder Repository class
@Repository
public interface IPolicyHolderRepository extends JpaRepository<PolicyHolder,Integer>
{	
	@Query("Select a from PolicyHolder a where a.agent=?1")
    List<PolicyHolder> findAllByAgent(Optional<Agent> agent);
	
	@Query("select p from PolicyHolder p where p.agent=null")
	List<PolicyHolder> viewIdlePolicyHolder();
	
	Optional<PolicyHolder> findByEmailId(String email);
}
