package akj1.cmu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import akj1.cmu.edu.model.AuthorModel;
//This class performs the functions like getting and setting author details
import akj1.cmu.edu.model.QuoteModel;

@Service
public class RandomService {
	
	// Reading the URL of author service 
	@Value("${quoteServiceURL}") private String quoteURL;
	
	@Value("${authorServiceURL}") private String authURL;
	
	// this string contains the final URL to hit.
	private String finalURL;
	// Used to call a rest service
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate = new RestTemplate();
	
	// Get random quote and its author
	@HystrixCommand(fallbackMethod = "randomQuoteBackup")
	public QuoteModel getRandomQuote() {
		// Make URL
		finalURL = quoteURL + "api/quote/random";
		// Call service
		QuoteModel randomQuote = restTemplate.getForObject(finalURL, QuoteModel.class);
		long authorID = randomQuote.getAuthorID();
				
		finalURL = authURL + "api/author/"+authorID;
		AuthorModel author = restTemplate.getForObject(finalURL, AuthorModel.class);
        randomQuote.setAuthor(author);
	    return randomQuote;
	}
	
	public QuoteModel randomQuoteBackup() {
		AuthorModel author = new AuthorModel("Abhi",5L);
		QuoteModel quote = new QuoteModel("Three things cannot be long hidden: the sun, the moon, and the truth.",
				"https://en.wikipedia.org/wiki",5L);
		
		quote.setAuthor(author);		
		return quote;
		
	}

}
