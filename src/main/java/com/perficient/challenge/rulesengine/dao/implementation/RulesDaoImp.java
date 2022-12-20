package com.perficient.challenge.rulesengine.dao.implementation;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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
	
	public void insert(Rule rule) {
		this.mongoTemplate.insert(rule);
	}
	
	@Override
	public List<Rule> findAll() {
		return this.mongoTemplate.findAll(Rule.class);
	}
	
	@Override
	public List<Rule> findPinned() {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("pinned").is(true)).with(Sort.by(Sort.Direction.DESC, "date"));
		
		return this.mongoTemplate.find(query, Rule.class);
	}
	
	@Override
	public List<Rule> findLastProcessed() {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("date").lte(System.currentTimeMillis())).with(Sort.by(Sort.Direction.DESC, "date"));
		
		return this.mongoTemplate.find(query, Rule.class);
	}
}