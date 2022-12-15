package com.perficient.challenge.rulesengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.repository.TransactionRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories("com.perficient.challenge.rulesengine.repository")
@ComponentScan(basePackages = {"com.perficient.challenge.rulesengine.controller", "com.perficient.challenge.rulesengine.model", "com.perficient.challenge.rulesengine.repository", "com.perficient.challenge.rulesengine.service"})
public class RulesEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesEngineApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(TransactionRepository transactionRespository) {
		
		return (args) -> {
			Transaction transaction = new Transaction();
			transaction.setId("T4");
			
			/*Map<String, String> jo = new HashMap<String, String>();
			jo.put("transactionid", "trx_9928271199");
			jo.put("amount", "12");
			jo.put("owner", "Antonio Fernandez");
			jo.put("bank", "Santander");
			jo.put("account", "98245897132");
			transaction.setData(jo);
			
			transactionRespository.save(transaction);*/
			
			List<Transaction> trans = transactionRespository.findAll();
			
			for(Transaction t: trans) {
				System.out.println(t.getData());
			}
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
