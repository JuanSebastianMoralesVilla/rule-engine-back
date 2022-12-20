package com.perficient.challenge.rulesengine.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.service.interfaces.RulesService;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

	@Autowired
	RulesService rulesService;

	@GetMapping("/")
	public ResponseEntity<List<Rule>> findAll() {

		Optional<List<Rule>> rules = this.rulesService.findAll();

		if (rules.isEmpty())
			return new ResponseEntity<List<Rule>>(rules.get(), HttpStatus.OK);

		return new ResponseEntity<List<Rule>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/lastProcessed")
	public ResponseEntity<List<Rule>> findLastProcessedRules() {
		Optional<List<Rule>> rules = this.rulesService.findLastProcessed();

		if (rules.isEmpty())
			return new ResponseEntity<List<Rule>>(rules.get(), HttpStatus.OK);

		return new ResponseEntity<List<Rule>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
