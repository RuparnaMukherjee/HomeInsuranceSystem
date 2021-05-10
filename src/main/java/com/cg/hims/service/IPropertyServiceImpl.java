package com.cg.hims.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Property;
import com.cg.hims.repository.IPropertyRepository;
@Service
@Transactional
public class IPropertyServiceImpl implements IPropertyService {

	@Autowired
	IPropertyRepository propertyDao;
	
	@Override
	public Property addProperty(Property property) {
		// TODO Auto-generated method stub
		propertyDao.save(property);
		return property;
	}

	@Override
	public Property viewProperty() {
		
		return null;
		// TODO Auto-generated method stub
		
	}

}
