package com.cg.hims;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.entities.Property;
import com.cg.hims.repository.IPropertyRepository;
import com.cg.hims.service.IPropertyServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProperty {
	@InjectMocks
	private IPropertyServiceImpl propertyService;
	@Mock
	private IPropertyRepository propertyRepository;

	@Test
	public void testAddProperty() {

		Property property = new Property();
		property.setProperty_id(1);

		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		assertEquals(property, propertyService.addProperty(property));
	}

	@Test
	public void testListProperty() {
		Property property = new Property();
		property.setProperty_id(1);

		Property property1 = new Property();
		property1.setProperty_id(2);

		List<Property> propertyList = new ArrayList<Property>();
		propertyList.add(property);
		propertyList.add(property);
		Mockito.when(propertyRepository.findAll()).thenReturn(propertyList);
		assertEquals(2, propertyService.viewAllProperty().size());
	}

	@Test
	public void testPositiveViewPropertyById() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);
		Mockito.when(propertyRepository.existsById(1)).thenReturn(true);
		Mockito.when(propertyRepository.findById(1)).thenReturn(Optional.of(property));

		assertEquals(property, propertyService.findPropertyByid(1).get());
	}

	@Test
	public void testNegativeViewPropertyById() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);
		Executable ex = () -> {
			propertyService.findPropertyByid(1);
		};
		verify(propertyRepository, never()).findById(1);
		Assertions.assertThrows(PropertyNotFoundException.class, ex);
	}

	@Test
	public void testNegativeEditPoperty() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);

		property.setProperty_id(1);

		Executable ex = () -> {
			propertyService.updateProperty(property);
		};
		verify(propertyRepository, never()).save(property);
		Assertions.assertThrows(PropertyNotFoundException.class, ex);
	}

	@Test
	public void testPositiveEditProperty() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);

		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		Mockito.when(propertyRepository.existsById(1)).thenReturn(true);

		assertEquals(property, propertyService.updateProperty(property));
	}

	@Test
	public void testPositiveDelete() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);
		Mockito.when(propertyRepository.existsById(1)).thenReturn(true);
		propertyService.RemoveProperty(1);
		verify(propertyRepository, Mockito.atLeastOnce()).deleteById(1);
	}

	@Test
	public void testNegativeDelete() throws PropertyNotFoundException {
		Property property = new Property();
		property.setProperty_id(1);
		Mockito.when(propertyRepository.existsById(2)).thenReturn(false);
		Executable ex = () -> {
			propertyService.RemoveProperty(2);
		};
		verify(propertyRepository, never()).deleteById(1);
		Assertions.assertThrows(PropertyNotFoundException.class, ex);
	}
}