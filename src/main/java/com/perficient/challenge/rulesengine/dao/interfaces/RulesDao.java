package com.perficient.challenge.rulesengine.dao.interfaces;

import java.util.List;

import com.perficient.challenge.rulesengine.model.Rule;

public interface RulesDao {

	public void save(Rule rule);
	public List<Rule> findAll();
	public List<Rule> findPinned();
	public List<Rule> findLastProcessed();
}
