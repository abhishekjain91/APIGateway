package akj1.cmu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import akj1.cmu.edu.model.AuthorModel;
//This class performs the functions like getting and setting author details

@Service
public class AuthorService {
	  // Reading the URL of author service 
	  @Value("${authorServiceURL}") private String authURL;
	  
	  // this string contains the final url to hit.
	  private String finalURL;
	  // Used to call a rest servcie
	  
	  @Autowired
	  @LoadBalanced
	  RestTemplate restTemplate = new RestTemplate();
	  
	  // Get author details by his ID
      public AuthorModel getAuthorById(Long authorID) {
    	  // Make URL
    	  finalURL = authURL+"api/author/"+authorID;
    	  // Call the service
          AuthorModel author = restTemplate.getForObject(finalURL, AuthorModel.class);
          return author;
      }
	  
      // Get author details by his Name
      public AuthorModel getAuthorByName(String authName) {
    	  // Make URL
    	  finalURL = authURL+"api/author/name/"+authName;
    	  // Call the service
          AuthorModel author = restTemplate.getForObject(finalURL, AuthorModel.class);
          return author;
      }
      
      // Save the Author
      public Long saveAuthor(String authName) {
    	  //
    	  HttpEntity<AuthorModel> newAuthor = new HttpEntity<>(new AuthorModel(authName, 0L));
          System.out.println(authName);
          // Make URL
          finalURL = authURL+"api/author";
          // Send the details about the author and get his new ID.
  	      ResponseEntity<Long> resp = restTemplate.exchange(finalURL, HttpMethod.POST, newAuthor, Long.class);
  	      // Fetch the ID
  	      Long authorID = resp.getBody();
  	      // return authorID
  	      return authorID;
      }
}