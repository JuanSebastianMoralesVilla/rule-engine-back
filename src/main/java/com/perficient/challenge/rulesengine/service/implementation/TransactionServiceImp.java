package com.perficient.challenge.rulesengine.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.dao.interfaces.TransactionsDao;
import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.processor.RuleProcessor;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService{

	@Autowired
	private TransactionsDao transactionDao;
	@Autowired
	private RuleProcessor ruleProcessor;

	@Override
	public List<Transaction> findAll(){
		return this.transactionDao.findAll();
	}

	@Override
	public Set<String> getColumns() {
		return this.transactionDao.getColumns();
	}

	@Override
	public List<Transaction> findByRule(String rule) {
		
		String formattedQuery = this.ruleProcessor.processRule(rule);
		List<Transaction> transactions = this.transactionDao.findByRule(formattedQuery);
	
		return transactions;
	}
	
}

