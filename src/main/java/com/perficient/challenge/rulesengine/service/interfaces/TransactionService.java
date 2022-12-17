package com.perficient.challenge.rulesengine.service.interfaces;

import java.util.List;
import java.util.Set;

import com.perficient.challenge.rulesengine.model.Transaction;

public interface TransactionService {

	public List<Transaction> findAll();
	public Set<String> getColumns();
}
