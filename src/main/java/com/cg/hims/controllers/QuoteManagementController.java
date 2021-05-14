package com.cg.hims.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.service.IQuoteServiceImpl;


/*Controller class for Quote Management
 * author:Ruparna Mukherjee
 * date created:09/05/2021
 */

@RestController
@RequestMapping("/quote")
public class QuoteManagementController {
	@Autowired
	IQuoteServiceImpl quoteImpl;
	
	//Check for controller
	@GetMapping("/Check")
	public String check() {
		return "checked";
	}
	
	//Create new quote in database
	@PostMapping("/CreateQuote")
	public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
		if(quote==null)
			return new ResponseEntity("Quote Empty",HttpStatus.NOT_FOUND);
		System.out.println("Quote Created");
		Quote quote1= quoteImpl.addQuote(quote);
		return new ResponseEntity <Quote>(quote ,HttpStatus.OK);
	}
	
	//Show all quotes from database
	@GetMapping("/ViewALlQuotes")
	public ResponseEntity<List<Quote>> showAllQuotes() {
		List<Quote> quote=quoteImpl.showAllQuotes();
		if(quote.isEmpty())
			return new ResponseEntity("Quote not found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Quote>>(quote,HttpStatus.OK);
	}
	
	//Show quote from id
	@GetMapping("/ViewQuoteById/{id}")
	public Optional<Quote> findQuoteById(@PathVariable("id") int id) throws QuoteNotFoundException {
		return quoteImpl.findQuoteById(id);
	}
	
	//Update quote 
	@PutMapping("/UpdateQuote")
	public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) throws QuoteNotFoundException{
		if(quote==null)
			return new ResponseEntity("enter the quote to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Quote>(quoteImpl.updateQuote(quote),HttpStatus.OK);
	}
	
	//Delete quote from database
	@DeleteMapping("/DeleteQuote/{id}")
	public String removeQuote(@PathVariable("id") int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		return quoteImpl.removeQuote(id);
	}

}
