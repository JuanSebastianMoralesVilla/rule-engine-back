package com.perficient.challenge.rulesengine.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/")
	public List<Transaction> findAll(){
		return transactionService.findAll();
	}
	
	@PostMapping("/columns")
	public Set<String> getColumns(){
		return transactionService.getColumns();
	}
}
