package com.perficient.challenge.rulesengine.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.dao.interfaces.TransactionsDao;
import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.processor.RuleProcessor;
import com.perficient.challenge.rulesengine.service.interfaces.RulesService;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService{

	@Autowired
	private TransactionsDao transactionDao;
	@Autowired
	private RulesService ruleService;
	@Autowired
	private RuleProcessor ruleProcessor;

	@Override
	public Optional<List<Transaction>> findAll(){
		List<Transaction> transactions = this.transactionDao.findAll();
		return Optional.ofNullable(transactions);
	}

	@Override
	public Optional<Set<String>> getColumns() {
		Set<String> columns = this.transactionDao.getColumns();
		return Optional.ofNullable(columns);
	}

	@Override
	public Optional<List<Transaction>> findByRule(String rule) {
		
		if(rule == null)
			return this.findAll();
		
		String formattedQuery = this.ruleProcessor.processRule(rule);
		
		List<Transaction> transactions;
		if(formattedQuery.isBlank()) 
			transactions = this.transactionDao.findAll();
		else 
			transactions = this.transactionDao.findByRule(formattedQuery);
		
		Optional<List<Transaction>> transactionsOptional = Optional.ofNullable(transactions);
		
		if(transactionsOptional.isPresent() && !transactionsOptional.get().isEmpty())
			this.ruleService.save(rule);

		return transactionsOptional;
	}
	
}

