package com.cg.hims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.hims.entities.Agent;
import com.cg.hims.exceptions.AgentNotFoundException;

public interface IAgentRepository extends JpaRepository<Agent,Integer>{


}
