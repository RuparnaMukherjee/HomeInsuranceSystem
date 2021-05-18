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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.service.IQuoteService;
import com.cg.hims.service.IQuoteServiceImpl;

/*
 * Controller class for Quote Management
 * Author:Ruparna Mukherjee
 * date created:09/05/2021
 * Version:1.0
 */

@RestController
@RequestMapping("/quote")
public class QuoteManagementController {

	/* Autowired Quote Service Class object */
	@Autowired
	IQuoteService quoteImpl;
	
	static final Logger LOGGER = LoggerFactory.getLogger(QuoteManagementController.class);

	/************************************************************************************
	 * Method: addQuote 
	 * Description: It is used to add quote into quote table
	 * @param quote: quote's reference variable.
	 * @returns quote: It returns quote with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 ************************************************************************************/

	@PostMapping("/CreateQuote")
	public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
		if (quote == null)
			return new ResponseEntity("Quote Empty", HttpStatus.NOT_FOUND);
		LOGGER.info("Quote Created");
		Quote quote1 = quoteImpl.addQuote(quote);
		return new ResponseEntity<Quote>(quote, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: showAllQuotes 
	 * Description: It is used to retrieve all the quotes from
	 *				 the quote table
	 * @returns List<quote>: It returns List of quote with details
	 * @GetMapping: annotated methods handle the HTTP GET requests matched with
	 *              given URI expression
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 ************************************************************************************/

	@GetMapping("/ViewAllQuotes")
	public ResponseEntity<List<Quote>> showAllQuotes() {
		List<Quote> quote = quoteImpl.showAllQuotes();
		if (quote.isEmpty())
			return new ResponseEntity("Quote not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Quote>>(quote, HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: findQuoteById 
	 * Description: It is used to retrieve quote from quote
	 * 				table by checking the id
	 * @returns quote: It returns quote with details
	 * @GetMapping: annotated methods handle the HTTP GET requests matched with
	 *              given URI expression
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/

	@GetMapping("/ViewQuoteById/{id}")
	public Optional<Quote> findQuoteById(@PathVariable("id") int id) throws QuoteNotFoundException {
		return quoteImpl.findQuoteById(id);
	}

	/************************************************************************************
	 * Method: updateQuote 
	 * Description: It is used to update a quote record from
	 * 				quote table
	 * @returns quote: It returns quote with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given
	 *              URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 ************************************************************************************/

	@PutMapping("/UpdateQuote")
	public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) throws QuoteNotFoundException {
		if (quote == null)
			return new ResponseEntity("enter the quote to be updated", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Quote>(quoteImpl.updateQuote(quote), HttpStatus.OK);
	}

	/************************************************************************************
	 * Method: deleteQuote 
	 * Description: It is used to delete a quote record from
	 * 				quote table
	 * @returns String: It returns string value specifying successful deletion or
	 *          failure of deletion
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with
	 *                 given URI expression.
	 * @PathVariable: It is used to handle template variables in the request URI
	 *                mapping.
	 ************************************************************************************/

	@DeleteMapping("/DeleteQuote/{id}")
	public String removeQuote(@PathVariable("id") int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		return quoteImpl.removeQuote(id);
	}

}
