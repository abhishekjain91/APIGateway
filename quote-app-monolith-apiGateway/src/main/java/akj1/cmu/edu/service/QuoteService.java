package akj1.cmu.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import akj1.cmu.edu.model.AuthorModel;
import akj1.cmu.edu.model.QuoteModel;

// This is responsble for getting and setting quotes
@Service
public class QuoteService {
	// Reading the URL of author service 
	@Value("${quoteServiceURL}") private String quoteURL;
	
	// this string contains the final URL to hit.
	private String finalURL;
	// Used to call a rest service
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate = new RestTemplate();
	
	// Get all quotes of one author
	public List<QuoteModel> getQuotes(Long authorID) {
		finalURL = quoteURL+"api/quote/" + authorID;
		// The response type would be Parameterized list of quotes
    	ParameterizedTypeReference<List<QuoteModel>> respType = new ParameterizedTypeReference<List<QuoteModel>>(){};
    	// Call service
        ResponseEntity<List<QuoteModel>> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, respType);
        return response.getBody();
	}
	public void saveQuote(QuoteModel quote, AuthorModel author) {
		HttpEntity<QuoteModel> newQuote = new HttpEntity<>(	new QuoteModel(quote.getText(), quote.getSource(), author.getId()));
		// MAke URL
		finalURL = quoteURL+"api/addquote";
		// CAll service and see if response is 200 OK or not?
		ResponseEntity<QuoteModel> response = restTemplate.exchange(finalURL, HttpMethod.POST, newQuote, QuoteModel.class);	    		    		
	}
}