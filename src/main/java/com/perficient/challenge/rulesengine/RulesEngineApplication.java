package com.perficient.challenge.rulesengine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
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
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
