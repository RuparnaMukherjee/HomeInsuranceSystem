package com.cg.hims.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.repository.IQuoteRepository;

/*
 * Quote Service Layer
 * Author:Ruparna Mukherjee
 * Version:1.0
 * date created:13/05/2021
 */

@Service
@Transactional
public class IQuoteServiceImpl implements IQuoteService{

	@Autowired
	IQuoteRepository quoteDao;      //Autowired quote repository object
	
	/************************************************************************************
	 * Method: addQuote 
	 * Description: It is used to add quote into quote table
	 * @returns quote: It returns quote object
	 *@Override It is used to override quote Service Interface
	 ************************************************************************************/
	
	@Override
	public Quote addQuote(Quote quote) {
		// TODO Auto-generated method stub
		quoteDao.save(quote);
		return quote;
	}
	
	/************************************************************************************
	 * Method: updateQuote 
	 * Description: It is used to update particular quote into quote table
	 * @returns quote: It returns quote object
	 *@Override It is used to override quote Service Interface
	 ************************************************************************************/

	@Override
	public Quote updateQuote(Quote quote) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(quote.getQuoteId()))
			throw new QuoteNotFoundException("quote not found");
		quoteDao.save(quote);
		return quote;
	}
	
	/************************************************************************************
	 * Method: findQuoteById 
	 * Description: It is used to find particular quote by quote id in quote table
	 * @returns quote: It returns the specific quote object if found otherwise throws exception
	 *@Override It is used to override quote Service Interface
	 ************************************************************************************/

	@Override
	public Optional<Quote> findQuoteById(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		Optional<Quote> quote=quoteDao.findById(id);
		if(!quoteDao.existsById(id))
			throw new QuoteNotFoundException("quote not found");
		return quoteDao.findById(id);
	}
	
	/************************************************************************************
	 * Method: removeQuote 
	 * Description: It is used to delete particular quote by quote id in quote table
	 * @returns String: It returns success message if quote is deleted otherwise throws error
	 *@Override It is used to override quote Service Interface
	 ************************************************************************************/

	@Override
	public String removeQuote(int id) throws QuoteNotFoundException {
		// TODO Auto-generated method stub
		if(!quoteDao.existsById(id))
			throw new QuoteNotFoundException("quote not found");
		quoteDao.deleteById(id);
		return "Quote deleted successfully";
	}
	
	/************************************************************************************
	 * Method: showAllQuotes
	 * Description: It is used show all the quotes with details from quote table
	 * @returns List quote: It returns list of quotes
	 *@Override It is used to override quote Service Interface
	 ************************************************************************************/

	@Override
	public List<Quote> showAllQuotes() {
		// TODO Auto-generated method stub
		List<Quote> quoteList=quoteDao.findAll();
		return quoteList;
	}

}
