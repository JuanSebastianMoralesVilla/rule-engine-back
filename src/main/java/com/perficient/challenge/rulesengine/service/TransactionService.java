package com.perficient.challenge.rulesengine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> findAll(){
		return transactionRepository.findAll();
	}
	
}

