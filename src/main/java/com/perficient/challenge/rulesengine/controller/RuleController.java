package com.perficient.challenge.rulesengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.service.RuleService;



@RestController
@RequestMapping("/api")
public class RuleController {

	@Autowired
	private RuleService rulesService;
	
	@GetMapping("/findAllRules")
	public List<Rule> getRule(){
		return rulesService.findAll();
	}
	
	@PostMapping("/addRules")
	public void addRule(@RequestBody Rule rule) {
		rulesService.save(rule);
	}
}
