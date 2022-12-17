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
import com.perficient.challenge.rulesengine.model.Rule;

@Component
public class RulesDaoImp {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void save(Rule rule) {
		this.mongoTemplate.save(rule);
	}
	
	public List<Rule> findAll() {
		return this.mongoTemplate.findAll(Rule.class);
	}
	
	public List<Rule> findPinned() {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("pinned").is(true)).with(Sort.by(Sort.Direction.DESC, "date"));
		
		return this.mongoTemplate.find(query, Rule.class);
	}
	
	public List<Rule> findLastProcessed() {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("date").lte(System.currentTimeMillis())).with(Sort.by(Sort.Direction.DESC, "date"));
		
		return this.mongoTemplate.find(query, Rule.class);
	}
}