package com.perficient.challenge.rulesengine.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.perficient.challenge.rulesengine.model.Transaction;

public interface TransactionService {

	public Optional<List<Transaction>>findAll();
	public Optional<Set<String>> getColumns();
	public Optional<List<Transaction>> findByRule(String rule);
}
