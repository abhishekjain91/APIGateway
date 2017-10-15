package akj1.cmu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class apiGateway {
    
    public static void main(String[] args) {
        SpringApplication.run(apiGateway.class, args);
	}
    
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
}

