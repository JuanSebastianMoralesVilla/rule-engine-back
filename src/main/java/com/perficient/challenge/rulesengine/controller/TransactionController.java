package com.perficient.challenge.rulesengine.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;



@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/")
	public List<Transaction> findAll(){
		return transactionService.findAll();
	}
	
	@PostMapping("/findByRule")
	public List<Transaction> findByRule(@RequestBody String rule){
		List<Transaction> transactions = this.transactionService.findByRule(rule);
		return transactions;
	}
	
	@GetMapping("/columns")
	public Set<String> getColumns(){
		return transactionService.getColumns();
	}
	
}
