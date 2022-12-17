package com.perficient.challenge.rulesengine.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.mongodbtemplate.MongoDbTemplate;

@Component
public class TransactionsDaoImp {

	@Autowired
	MongoDbTemplate mongoTemplate;

	public List<Transaction> findByRule(String column, String comparator, String value) {

		StringBuilder sb = new StringBuilder();
		sb.append("this.data.").append(column).append(" ").append(comparator).append(" ").append(value);

		return this.mongoTemplate.executeCustomQuery(sb.toString());
	}
	
	public List<String> getColumns() {

		List<String> l = new ArrayList<String>();
		l.add("first_name");
		l.add("last_name");
		l.add("amount");
		l.add("accepted");
		
		return l;
	}
}


/*((this.data.amount > 20000) && (this.data.fee > 0.11)) || (this.data.accepted = true)*/