package com.cg.hims.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.Policy;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.entities.Property;
import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.service.IAgentServiceImpl;
import com.cg.hims.service.IPolicyHolderServiceImpl;
import com.cg.hims.service.IPolicyServiceImpl;
import com.cg.hims.service.IPropertyServiceImpl;
import com.cg.hims.service.IQuoteServiceImpl;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	IAgentServiceImpl serviceobj;
	
	@Autowired
	IQuoteServiceImpl quoteImpl;
	
	@Autowired
	IPolicyHolderServiceImpl pholderImpl;
	
	@Autowired
	IPropertyServiceImpl propertyimpl;
	
	@Autowired
	IPolicyServiceImpl policyImpl;
	
	//1. Update Agent Details
	@PutMapping("/agentUpdate")
	public ResponseEntity<Agent> UpdateEmployee(@RequestBody Agent agent) throws AgentNotFoundException {
		if(agent==null)
			return new ResponseEntity("Enter Proper Agent Details",HttpStatus.NOT_FOUND);
		return new ResponseEntity(serviceobj.updateAgent(agent),HttpStatus.OK);

	}
	
	//2. Find Agent By Id
	@GetMapping("/findAgent/{id}")
	public ResponseEntity<Optional<Agent>> findAgentById(@PathVariable("id") int id) throws AgentNotFoundException {
		Optional<Agent> findagent= serviceobj.findAgentById(id);
		if(findagent==null)
			return new ResponseEntity("No Agent Found for given Id",HttpStatus.NOT_FOUND);
		return new ResponseEntity(findagent,HttpStatus.OK);
	}
	
	//3. Add Quote to Database
	@PostMapping("/CreateQuote")
	public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
		if(quote==null)
			return new ResponseEntity("Quote Empty",HttpStatus.NOT_FOUND);
		if(quote.getPolicy()==null || quote.getProperty()==null)
			return new ResponseEntity("Policy or Property missing",HttpStatus.NOT_FOUND);
		System.out.println("Quote Created");
		Quote quote1= quoteImpl.addQuote(quote);
		return new ResponseEntity <Quote>(quote ,HttpStatus.OK);
	}
	
	//4. Show all quotes from database
	@GetMapping("/ViewALlQuotes")
	public ResponseEntity<List<Quote>> showAllQuotes() {
		List<Quote> quote=quoteImpl.showAllQuotes();
		if(quote.isEmpty())
			return new ResponseEntity("Quote not found",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Quote>>(quote,HttpStatus.OK);
	}
	
	//5. Show quote from id
	@GetMapping("/ViewQuoteById/{id}")
	public Optional<Quote> findQuoteById(@PathVariable("id") int id) throws QuoteNotFoundException {
		return quoteImpl.findQuoteById(id);
	}
	
	//6. Update quote 
	@PutMapping("/UpdateQuote")
	public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) throws QuoteNotFoundException{
		if(quote==null)
			return new ResponseEntity("enter the quote to be updated",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Quote>(quoteImpl.updateQuote(quote),HttpStatus.OK);
	}
	
	//7. View Policy Holders
	@GetMapping("/ViewPolicyHolders/{agentId}")
    public ResponseEntity<List<PolicyHolder>> viewPolicyHolder(@PathVariable("agentId") int id) throws AgentNotFoundException {
    	
    	List<PolicyHolder> policy_holders= pholderImpl.viewPolicyHolderByAgentId(id);
    	
    	if(policy_holders.isEmpty())
			return new ResponseEntity("No Policy Holders under given Agent Id",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<PolicyHolder>>(policy_holders,HttpStatus.OK);
	}
	
	//8. View Policies
	@GetMapping("/ViewPolicies/{agentId}")
    public ResponseEntity<List<Policy>> viewPolicy(@PathVariable("agentId") int id) throws AgentNotFoundException {
    	
    	List<Policy> policies= policyImpl.viewPolicyByAgentId(id);
    	
    	if(policies.isEmpty())
			return new ResponseEntity("No Policy Holders under given Agent Id",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Policy>>(policies,HttpStatus.OK);
	}
	
	//9. Add Property Details
	@PostMapping("/addProperty")
	public ResponseEntity<Property> addProperty(@RequestBody Property property) {
		
		if(property==null)
			return new ResponseEntity("Property empty",HttpStatus.NOT_FOUND);
		System.out.println("Property added ");
		Property pro=propertyimpl.addProperty(property);
		return new ResponseEntity<Property>(property,HttpStatus.OK);
	}
	
	//10. View Properties
	@GetMapping("/ViewProperties/{agentId}")
    public ResponseEntity<List<Property>> viewProperty(@PathVariable("agentId") int id) {
    	
    	List<Property> properties= propertyimpl.viewProperty(id);
    	
    	if(properties.isEmpty())
			return new ResponseEntity("No Properties under given Agent Id",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Property>>(properties,HttpStatus.OK);
	}
	
}
