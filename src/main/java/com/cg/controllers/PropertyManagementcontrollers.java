package com.cg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.hims.entities.Property;
import com.cg.hims.service.IPropertyServiceImpl;
@Controller
public class PropertyManagementcontrollers {
	@Autowired
	IPropertyServiceImpl propertyimpl;
	
	@PostMapping("/addProperty")
	public Property addProperty(@RequestBody Property property) {
		return propertyimpl.addProperty(property);
	}
	
	public Property ViewProperty()
	{
		return null;
		
	}

}
