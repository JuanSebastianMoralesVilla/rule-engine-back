package com.perficient.challenge.rulesengine.dao.implementation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.perficient.challenge.rulesengine.dao.interfaces.RulesDao;
import com.perficient.challenge.rulesengine.model.Rule;

@Component
public class RulesDaoImp implements RulesDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void save(Rule rule) {
		this.mongoTemplate.save(rule);
	}
	
	@Override
	public List<Rule> findAll() {
		return this.mongoTemplate.findAll(Rule.class);
	}
	
	@Override
	public List<Rule> findLastProcessed() {
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "date")).limit(5);
		
		return this.mongoTemplate.find(query, Rule.class);
	}
}