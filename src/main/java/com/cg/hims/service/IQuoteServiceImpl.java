package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.repository.IQuoteRepository;

@Service
@Transactional
public class IQuoteServiceImpl implements IQuoteService{

	@Autowired
	IQuoteRepository quoteDao;
	
	//Add quote or create Quote
	@Override
	public Quote addQuote(Quote quote)       
	{
		// TODO Auto-generated method stub
		quoteDao.save(quote);        
		return quote;
	}
        
	//Update Existing Quote 
	@Override
	public Quote updateQuote(Quote quote) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(quote.getQuoteId()))                   //If Quoteid doesn't exists then throws appropriate exception else save and return quote object
			throw new QuoteNotFoundException("quote not found");
		quoteDao.save(quote);          
		return quote;
	}

	//Getting a quote by Id
	@Override
	public Optional<Quote> findQuoteById(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(id))        //
			throw new QuoteNotFoundException("quote not found");
		return quoteDao.findById(id);
	}

	//Remove any Existing Quote
	@Override
	public String removeQuote(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(id))
			throw new QuoteNotFoundException("quote not found");
		quoteDao.deleteById(id);
		return "Quote deleted successfully";
	}

	//View All Quotes 
	@Override
	public List<Quote> showAllQuotes() {
		// TODO Auto-generated method stub
		List<Quote> quoteList=quoteDao.findAll();
		return quoteList;
	}

}
