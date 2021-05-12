package com.cg.hims.service;

import java.util.*;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface IQuoteService {

	public Quote addQuote(Quote quote);

	public Quote updateQuote(Quote quote) throws QuoteNotFoundException;

	public Optional<Quote> findQuoteById(int id) throws QuoteNotFoundException;

	public String removeQuote(int id) throws QuoteNotFoundException;

	public List<Quote> showAllQuotes();

}
