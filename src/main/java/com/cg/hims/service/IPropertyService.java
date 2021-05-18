package com.cg.hims.service;

import java.util.List;

import java.util.Optional;



import org.springframework.stereotype.Service;

import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.entities.Property;

public interface IPropertyService {
		
	public Property addProperty(Property property);

	public Property updateProperty(Property property) throws PropertyNotFoundException;
	
	public Optional<Property> findPropertyByid(int id) throws PropertyNotFoundException;
	
	public String RemoveProperty(int id) throws PropertyNotFoundException;
	
	public List<Property> viewAllProperty();
	

}
