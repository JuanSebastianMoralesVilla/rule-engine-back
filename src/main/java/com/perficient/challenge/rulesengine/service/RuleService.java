package com.perficient.challenge.rulesengine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.repository.RulesRepository;

@Service
public class RuleService {

	@Autowired
	private RulesRepository rr;

	public List<Rule> findAll(){
		return rr.findAll();
	}
	
}

