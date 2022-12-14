package com.perficient.challenge.rulesengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.perficient.challenge.rulesengine.controller.RuleController;
import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.repository.RulesRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories("com.perficient.challenge.rulesengine.repository")
@ComponentScan(basePackages = {"com.perficient.challenge.rulesengine.controller", "com.perficient.challenge.rulesengine.model", "com.perficient.challenge.rulesengine.repository", "com.perficient.challenge.rulesengine.service"})
public class RulesEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesEngineApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(RulesRepository rr) {
		
		return (args) -> {
			Rule rule = new Rule();
			rule.setID("asd12312");
			
			rr.save(rule);
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
