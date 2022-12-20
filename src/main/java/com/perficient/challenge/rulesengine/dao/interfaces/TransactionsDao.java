package com.perficient.challenge.rulesengine.dao.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.perficient.challenge.rulesengine.model.Transaction;

public interface TransactionsDao {

	public List<Transaction> findAll();
	public List<Transaction> findByRule(String customQuery);
	public Set<String> getColumns();
}
