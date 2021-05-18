package com.cg.hims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;

/* Repository for Quote */

@Repository
public interface IQuoteRepository extends JpaRepository<Quote, Integer> {

}
