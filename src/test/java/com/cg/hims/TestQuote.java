package com.cg.hims;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

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

import com.cg.hims.entities.Policy;
import com.cg.hims.entities.Property;
import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.repository.IQuoteRepository;
import com.cg.hims.service.IQuoteService;
import com.cg.hims.service.IQuoteServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQuote {
	
	@InjectMocks
	private IQuoteServiceImpl quoteService;
	@Mock
	private IQuoteRepository quoteRepository;
	
	@Test
	public void testAddQuote() {
		
		Property property=new Property();
		property.setProperty_id(1);
		
		Policy policy=new Policy();
		
		policy.setPolicyId(1);
		Quote quote=new Quote();
		quote.setQuoteId(1);
		quote.setProperty(property);
		quote.setPolicy(policy);
		Mockito.when(quoteRepository.save(quote)).thenReturn(quote);
		assertEquals(quote,quoteService.addQuote(quote));
	}
	
	@Test
	public void testListQuote() {
		Property property=new Property();
		property.setProperty_id(1);
		
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		Quote quote=new Quote();
		quote.setQuoteId(1);
		quote.setProperty(property);
		quote.setPolicy(policy);
		
		Property property1=new Property();
		property1.setProperty_id(2);
		
		Policy policy1=new Policy();
		policy.setPolicyId(2);
		
		Quote quote1=new Quote();
		quote.setQuoteId(2);
		quote.setProperty(property);
		quote.setPolicy(policy);
		
		List<Quote> quoteList=new ArrayList<Quote>();
		quoteList.add(quote);
		quoteList.add(quote1);
		Mockito.when(quoteRepository.findAll()).thenReturn(quoteList);
		assertEquals(2,quoteService.showAllQuotes().size());
	}
	
	@Test
	public void testPositiveViewQuoteById() throws QuoteNotFoundException {
		Quote quote=new Quote();
		quote.setQuoteId(1);
		Mockito.when(quoteRepository.existsById(1)).thenReturn(true);
		Mockito.when(quoteRepository.findById(1)).thenReturn(Optional.of(quote));
		
		assertEquals(quote.getQuoteId(),quoteService.findQuoteById(1).get().getQuoteId());
	}
	
	@Test
	public void testNegativeViewQuoteById() throws QuoteNotFoundException {
		Quote quote=new Quote();
		quote.setQuoteId(1);
		Executable ex=()->{
			quoteService.findQuoteById(2);
		};
		verify(quoteRepository,never()).findById(1);
		Assertions.assertThrows(QuoteNotFoundException.class, ex);
	}
	
	@Test 
	public void testNegativeEditQuote()throws QuoteNotFoundException {
		Property property=new Property();
		property.setProperty_id(1);
		
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		Quote quote=new Quote();
		quote.setQuoteId(1);
		quote.setProperty(property);
		quote.setPolicy(policy);
		Executable ex=()->{
			quoteService.updateQuote(quote);
		};
		verify(quoteRepository,never()).save(quote);
		Assertions.assertThrows(QuoteNotFoundException.class,ex);
	}
	
	@Test
	public void testPositiveEditQuote()throws QuoteNotFoundException{
		Property property=new Property();
		property.setProperty_id(1);
		
		Policy policy=new Policy();
		policy.setPolicyId(1);
		
		Quote quote=new Quote();
		quote.setQuoteId(1);
		quote.setProperty(property);
		quote.setPolicy(policy);
		Mockito.when(quoteRepository.save(quote)).thenReturn(quote);
		Mockito.when(quoteRepository.existsById(1)).thenReturn(true);
		
		assertEquals(quote,quoteService.updateQuote(quote));
	}
	
	@Test 
	public void testPositiveDelete()throws QuoteNotFoundException{
		Quote quote=new Quote();
		quote.setQuoteId(1);
		Mockito.when(quoteRepository.existsById(1)).thenReturn(true);
			quoteService.removeQuote(1);
		verify(quoteRepository,Mockito.atLeastOnce()).deleteById(1);
	}
	
	@Test 
	public void testNegativeDelete()throws QuoteNotFoundException{
		Quote quote=new Quote();
		quote.setQuoteId(1);
		Mockito.when(quoteRepository.existsById(2)).thenReturn(false);
		Executable ex=()->{
			quoteService.removeQuote(2);
		};
		verify(quoteRepository,never()).deleteById(1);
		Assertions.assertThrows(QuoteNotFoundException.class,ex);
	}
		

}
