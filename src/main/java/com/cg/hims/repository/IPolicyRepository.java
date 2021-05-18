package com.cg.hims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;

public interface IPolicyRepository extends JpaRepository<Policy,Integer> {

	@Query("Select p from Policy p  where p.agent=?1")
    List<Policy> findAllByAgent(Optional<Agent> agent);

}
