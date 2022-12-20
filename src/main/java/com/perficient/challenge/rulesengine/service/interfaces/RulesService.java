package com.perficient.challenge.rulesengine.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.perficient.challenge.rulesengine.model.Rule;

public interface RulesService {

	public void save(String rule);
	public Optional<List<Rule>> findAll();
	public Optional<List<Rule>> findLastProcessed();
}
