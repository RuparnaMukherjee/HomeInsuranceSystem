package com.cg.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping("/quote")
public class QuoteManagementController {
	@Autowired
	IQuoteServiceImpl quoteImpl;
	
	@GetMapping("/Check")
	public String check() {
		return "checked";
	}
	@PostMapping("/CreateQuote")
	public Quote addQuote(@RequestBody Quote quote) {
		System.out.println("Quote Created");
		return quoteImpl.addQuote(quote);
	}
	@GetMapping("/ViewALlQuotes")
	public List<Quote> showAllQuotes() {
		return quoteImpl.showAllQuotes();
	}
	@GetMapping("/ViewQuoteById/{id}")
	public Optional<Quote> findQuoteById(@PathVariable("id") int id) throws QuoteNotFoundException {
		return quoteImpl.findQuoteById(id);
	}
	@PutMapping("/UpdateQuote")
	public Quote updateQuote(@RequestBody Quote quote) throws QuoteNotFoundException{
		return quoteImpl.updateQuote(quote);
	}
	@DeleteMapping("/DeleteQuote/{id}")
	public String removeQuote(@PathVariable("id") int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		return quoteImpl.removeQuote(id);
	}

}
