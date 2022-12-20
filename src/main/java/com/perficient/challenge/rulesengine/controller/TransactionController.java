package com.perficient.challenge.rulesengine.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;



@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/")
	public ResponseEntity<List<Transaction>> findAll(){
		
		Optional<List<Transaction>> transactions = transactionService.findAll();
		
		if(transactions.isPresent())
			return new ResponseEntity<List<Transaction>>(transactions.get(), HttpStatus.OK);
		
		return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/findByRule")
	public ResponseEntity<List<Transaction>> findByRule(@RequestBody String rule){
		Optional<List<Transaction>> transactions = this.transactionService.findByRule(rule);
		if(transactions.isPresent())
			return new ResponseEntity<List<Transaction>>(transactions.get(), HttpStatus.OK);
		
		return new ResponseEntity<List<Transaction>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/columns")
	public ResponseEntity<Set<String>> getColumns(){
		
		Optional<Set<String>> columns = this.transactionService.getColumns();
		
		if(columns.isPresent())
			return new ResponseEntity<Set<String>>(columns.get(), HttpStatus.OK);
		
		return new ResponseEntity<Set<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
