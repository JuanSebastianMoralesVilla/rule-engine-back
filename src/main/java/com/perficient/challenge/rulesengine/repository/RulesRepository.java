package com.perficient.challenge.rulesengine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.perficient.challenge.rulesengine.model.Rule;


public interface RulesRepository extends MongoRepository<Rule, String>{

}
