package com.perficient.challenge.rulesengine;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.perficient.challenge.rulesengine.dao.implementation.RulesDaoImp;
import com.perficient.challenge.rulesengine.dao.implementation.TransactionsDaoImp;
import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.mongodbtemplate.MongoDbTemplate;
import com.perficient.challenge.rulesengine.processor.RuleProcesor;
import com.perficient.challenge.rulesengine.repository.RulesRepository;
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
	public CommandLineRunner add(TransactionsDaoImp transactiondao, RuleProcesor rule, RulesDaoImp rulesdao) {
		
		return (args) -> {
			
			String ro = rule.processRule("((first_name=='Nev')AND(amount>=100))OR(accepted==true)");
			//mongo.executeCustomQuery(r);
			
			/*Rule r = new Rule();
			r.setId("treesss");
			r.setRule(ro);
			r.setPinned(false);
			r.setDate(System.currentTimeMillis());
			
			rulesdao.save(r);
			*/
			
			System.out.println(transactiondao.getColumns());
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
