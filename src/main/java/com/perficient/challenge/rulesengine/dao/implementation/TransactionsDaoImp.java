package com.perficient.challenge.rulesengine.dao.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.mongodbtemplate.MongoDbTemplate;

@Component
public class TransactionsDaoImp {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Transaction> findByRule(String column, String comparator, String value) {

		StringBuilder customQuery = new StringBuilder();
		customQuery.append("this.data.").append(column).append(" ").append(comparator).append(" ").append(value);

		Criteria criteria = new Criteria() {
			@Override
			public Document getCriteriaObject() {
				DBObject obj = new BasicDBObject();
				obj.put("$where", customQuery);
				return new Document(obj.toMap());
			}
		};

		Query query = Query.query(criteria);

		List<Transaction> transactions = mongoTemplate.find(query, Transaction.class);
		System.out.println("Custom -> " + transactions);
		return transactions;
	}

	public Set<String> getColumns() {
		
		Query query = new Query();
		Transaction transaction = this.mongoTemplate.findOne(query, Transaction.class);
		
		return transaction.getData().keySet();
	}
}

/*
 * ((this.data.amount > 20000) && (this.data.fee > 0.11)) || (this.data.accepted
 * = true)
 */