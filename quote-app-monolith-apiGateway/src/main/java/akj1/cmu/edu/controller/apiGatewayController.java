package akj1.cmu.edu.controller;

import java.util.List;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import akj1.cmu.edu.model.AuthorModel;
import akj1.cmu.edu.model.QuoteModel;
import akj1.cmu.edu.service.AuthorService;
import akj1.cmu.edu.service.QuoteService;
import akj1.cmu.edu.service.RandomService;


// Controller which drives the whole process
@EnableCircuitBreaker
@RestController
public class apiGatewayController {
	
	
	@Autowired
	QuoteService quoteservice;
	@Autowired
	AuthorService authorservice;
	
	@Autowired
	RandomService randomservice;

	
	// Get random quote
	 	@RequestMapping("/api/quote/random")
	    public QuoteModel random() {
		 	
	 		QuoteModel randomQuote = randomservice.getRandomQuote();
	    	//AuthorModel author = authorservice.getAuthorById(randomQuote.getAuthorID());
	    	//randomQuote.setAuthor(author);
	        return randomQuote;
	    }
	
 	
	 	// Save new quotes
	    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
	    public void saveQuote(@RequestBody QuoteModel quote) {
	    	String authorName = quote.getAuthor().getName();
	    	
	    	AuthorModel author = authorservice.getAuthorByName(authorName);
	    	// Author not found
	    	if (author == null) {
	    		long authorID = authorservice.saveAuthor(authorName);
	    		author = new AuthorModel(authorName, authorID);
	    	    quoteservice.saveQuote(quote, author);		    		    		
	    	} 
	    	else
	    	{
	    		quoteservice.saveQuote(quote, author);
	    	}
	    }
	    @RequestMapping(value = "/api/author/{authorID}")
	    public AuthorModel getQuotes(@PathVariable long authorID) {
	    	List<QuoteModel> quotes = quoteservice.getQuotes(authorID);
	        AuthorModel author = authorservice.getAuthorById(authorID);
	        author.setQuotes(quotes);
	        return author;
	    }
	    
}
