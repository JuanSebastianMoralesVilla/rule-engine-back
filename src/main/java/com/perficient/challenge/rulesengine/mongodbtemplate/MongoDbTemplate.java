package com.perficient.challenge.rulesengine.mongodbtemplate;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.perficient.challenge.rulesengine.model.Rule;
import com.perficient.challenge.rulesengine.model.Transaction;

@Component
public class MongoDbTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Transaction> executeCustomQuery(String customQuery) {

		Criteria criteria = new Criteria() {
			@Override
			public Document getCriteriaObject() {
				DBObject obj = new BasicDBObject();
				obj.put("$where", customQuery);
				return new Document(obj.toMap());
			}
		};

		Query query = Query.query(criteria);

		List<Transaction> documents = mongoTemplate.find(query, Transaction.class);
		System.out.println("Custom -> "+documents);
		return documents;
	}
}
