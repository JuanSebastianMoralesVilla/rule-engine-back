package com.perficient.challenge.rulesengine.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trans")
public class Transaction {

	@Id
	private String id;

	private Map<String, String> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return data.get("first_name");
	}
}
