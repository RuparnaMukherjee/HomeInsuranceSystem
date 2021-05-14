package com.cg.hims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.hims.entities.*;

public interface IPropertyRepository extends JpaRepository<Property,Integer>{
	
	@Query("Select x from Property x where x=(Select q.property from Quote q where q.policy=(Select p from Policy p where p.agent=?1))")
    List<Property> findAllByAgent(Optional<Agent> agent);
}
