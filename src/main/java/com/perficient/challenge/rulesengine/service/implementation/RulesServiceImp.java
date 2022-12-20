package com.perficient.challenge.rulesengine.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.dao.interfaces.RulesDao;
import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.service.interfaces.RulesService;

@Service
public class RulesServiceImp implements RulesService{
	
	@Autowired
	private RulesDao rulesDao;
	
	@Override
	public void save(String ruleString) {
		
		Rule rule = new Rule();
		rule.setId(UUID.randomUUID().toString());
		rule.setDate(System.currentTimeMillis());
		rule.setPinned(true);
		rule.setRule(ruleString);
		this.rulesDao.save(rule);
	}

	@Override
	public Optional<List<Rule>> findAll() {
		
		List<Rule> rules = this.rulesDao.findAll();
	
		return Optional.ofNullable(rules);
	}
	
	@Override
	public Optional<List<Rule>> findLastProcessed() {
		
		List<Rule> rules = this.rulesDao.findLastProcessed();
		
		return Optional.ofNullable(rules);
	}
}
