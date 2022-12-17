package com.perficient.challenge.rulesengine.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.dao.interfaces.TransactionsDao;
import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.repository.TransactionRepository;
import com.perficient.challenge.rulesengine.service.interfaces.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService{

	@Autowired
	private TransactionsDao transactionDao;

	@Override
	public List<Transaction> findAll(){
		return transactionDao.findAll();
	}

	@Override
	public Set<String> getColumns() {
		return transactionDao.getColumns();
	}
	
}

