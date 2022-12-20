package com.perficient.challenge.rulesengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rules")
public class Rule {

	@Id
	private String id;
	private String rule;
	private Long date;
	private boolean pinned;
	
	
	
	public Rule(String id, String rule, Long date, boolean pinned) {
		this.id = id;
		this.rule = rule;
		this.date = date;
		this.pinned = pinned;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public boolean isPinned() {
		return pinned;
	}
	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}
}