package com.cg.hims.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.entities.Property;

import com.cg.hims.service.IPropertyServiceImpl;
@RestController
@RequestMapping("/property")
public class PropertyManagementController{
	@Autowired
	IPropertyServiceImpl propertyimpl;
	
	@PostMapping("/addProperty")
	public ResponseEntity<Property> addProperty(@RequestBody Property property) {
		
		if(property.getProperty_id()==0)
			return new ResponseEntity("Property empty",HttpStatus.NOT_FOUND);
		System.out.println("Property added ");
		Property pro=propertyimpl.addProperty(property);
		return new ResponseEntity<Property>(property,HttpStatus.OK);
	}
	
	
	@GetMapping("/ViewAllProperty")
	public ResponseEntity<List<Property>> showAllProperty(){
		List<Property>property=propertyimpl.viewAllProperty();
		if(property.isEmpty())
			return new ResponseEntity("Property Not Found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Property>>(property,HttpStatus.OK);
	}
	
	
	@GetMapping("/ViewPropertyById/{id}")
	public Optional<Property> findPropertyById(@PathVariable("id") int id) throws PropertyNotFoundException
	{
		return propertyimpl.findPropertyByid(id);
	}
	
	
	@PutMapping("/updateProperty")
	public ResponseEntity<Property> updateProperty(@RequestBody Property property) throws PropertyNotFoundException
	{
		if(property==null)
			return new ResponseEntity("enter the property to be updated:",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Property>(propertyimpl.updateProperty(property),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/DeleteProperty/{id}")
	public String DeleteProperty(@PathVariable("id")int id) throws PropertyNotFoundException
{
		return propertyimpl.RemoveProperty(id);
}
}