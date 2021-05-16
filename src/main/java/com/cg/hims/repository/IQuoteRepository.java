package com.cg.hims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;

public interface IQuoteRepository extends JpaRepository<Quote, Integer> {

	//public Quote addQuote(Quote quote);

	//public Quote updateQuote(Quote quote) throws QuoteNotFoundException;

	//public Quote findQuoteById(int id) throws QuoteNotFoundException;

	//public Quote removeQuote(int id) throws QuoteNotFoundException;
	
	//public List<Quote> showAllQuotes();

}
