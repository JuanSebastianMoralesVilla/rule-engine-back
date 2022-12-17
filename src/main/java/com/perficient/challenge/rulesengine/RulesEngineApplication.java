package com.perficient.challenge.rulesengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import com.perficient.challenge.rulesengine.mongodbtemplate.MongoDbTemplate;
import com.perficient.challenge.rulesengine.processor.RuleProcesor;
import com.perficient.challenge.rulesengine.repository.TransactionRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories("com.perficient.challenge.rulesengine.repository")
@ComponentScan(basePackages = {"com.perficient.challenge.rulesengine"})
public class RulesEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesEngineApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(TransactionRepository transactionRespository, MongoDbTemplate mongo, RuleProcesor rule) {
		
		return (args) -> {
			
			String r = rule.processRule("((first_name=='Nev')AND(amount>=100))OR(accepted==true)");
			mongo.executeCustomQuery(r);
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
