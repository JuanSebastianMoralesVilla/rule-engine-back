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
import com.perficient.challenge.rulesengine.processor.RuleProcessor;
import com.perficient.challenge.rulesengine.service.implementation.TransactionServiceImp;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories("com.perficient.challenge.rulesengine.repository")
@ComponentScan(basePackages = {"com.perficient.challenge.rulesengine"})
public class RulesEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesEngineApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(TransactionsDaoImp transactiondao, RuleProcessor rule, RulesDaoImp rulesdao, TransactionServiceImp transer) {
		
		return (args) -> {
			
			//String ro = rule.processRule("((first_name=='Nev')AND(amount>=100))OR(accepted==true)");
			
			//mongo.executeCustomQuery(r);
			
			/*Rule r = new Rule();
			r.setId("treesss");
			r.setRule(ro);
			r.setPinned(false);
			r.setDate(System.currentTimeMillis());
			
			rulesdao.save(r);
			*/
			
			//System.out.println("Valid? -> "+ rule.verifyColumn("((first_name==100)AND(amount>=100))OR(accepted==true)", "first_name"));
			//System.out.println(rule.verifyRule("((first_name=='Nev')AND(amount>100))OR(accepted==true)"));
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
