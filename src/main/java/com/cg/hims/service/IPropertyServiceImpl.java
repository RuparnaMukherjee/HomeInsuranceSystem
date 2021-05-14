package com.cg.hims.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Property;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.repository.IPropertyRepository;

@Service
@Transactional
public class IPropertyServiceImpl implements IPropertyService {

	@Autowired
	IPropertyRepository propertyDao;
	
	@Autowired
    IAgentRepository agentDao;
	
	@Override
	public Property addProperty(Property property) {
		// TODO Auto-generated method stub
		propertyDao.save(property);
		return property;
	}

	

	@Override
	public Property updateProperty(Property property) throws PropertyNotFoundException {
		
		if(!propertyDao.existsById(property.getProperty_id()))
		   throw new PropertyNotFoundException("No Properties available");
		propertyDao.save(property);
		return property;
	}

	@Override
	public Optional<Property> findPropertyByid(int id) throws PropertyNotFoundException {
		
		
		// TODO Auto-generated method stub
		if(!propertyDao.existsById(id))
			throw new PropertyNotFoundException("Property not found");
		
		return propertyDao.findById(id);
	}

	@Override
	public String RemoveProperty(int id) throws PropertyNotFoundException {
	
		// TODO Auto-generated method stub
		if(!propertyDao.existsById(id))
          throw new PropertyNotFoundException("Property Not Found");
          
		propertyDao.deleteById(id);
		return "Successfully Deleted";
	}

	@Override
	public List<Property> viewAllProperty() {
		// TODO Auto-generated method stub
		List<Property> propertyList=propertyDao.findAll();
		return propertyList;
	}
	
	//View properties by agent id
    public List<Property> viewProperty(int id) {
		// TODO Auto-generated method stub
    	Optional<Agent> agent=agentDao.findById(id);
		return propertyDao.findAllByAgent(agent);
	}

}
