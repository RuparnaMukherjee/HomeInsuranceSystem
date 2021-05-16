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
	@Override
	public Quote addQuote(Quote quote) {
		// TODO Auto-generated method stub
		quoteDao.save(quote);
		return quote;
	}

	@Override
	public Quote updateQuote(Quote quote) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(quote.getQuoteId()))
			throw new QuoteNotFoundException("quote not found");
		quoteDao.save(quote);
		return quote;
	}

	@Override
	public Optional<Quote> findQuoteById(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		Optional<Quote> quote=quoteDao.findById(id);
		if(!quoteDao.existsById(id))
			throw new QuoteNotFoundException("quote not found");
		return quoteDao.findById(id);
	}

	@Override
	public String removeQuote(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(id))
			throw new QuoteNotFoundException("quote not found");
		quoteDao.deleteById(id);
		return "Quote deleted successfully";
	}

	@Override
	public List<Quote> showAllQuotes() {
		// TODO Auto-generated method stub
		List<Quote> quoteList=quoteDao.findAll();
		return quoteList;
	}

}
