package com.perficient.challenge.rulesengine.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perficient.challenge.rulesengine.model.Rule;

@Repository
public interface RulesRepository extends MongoRepository<Rule, String>{

	
	public List<Rule> findAll();
	
}
