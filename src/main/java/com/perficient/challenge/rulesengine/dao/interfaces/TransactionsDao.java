package com.perficient.challenge.rulesengine.dao.interfaces;

import java.util.List;
import java.util.Set;

import com.perficient.challenge.rulesengine.model.Transaction;

public interface TransactionsDao {

	public List<Transaction> findAll();
	public List<Transaction> findByRule(String column, String comparator, String value);
	public Set<String> getColumns();
}
