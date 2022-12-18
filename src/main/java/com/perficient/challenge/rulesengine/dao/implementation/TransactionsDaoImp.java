package com.perficient.challenge.rulesengine.dao.implementation;

import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.perficient.challenge.rulesengine.dao.interfaces.TransactionsDao;
import com.perficient.challenge.rulesengine.model.Transaction;

@Component
public class TransactionsDaoImp implements TransactionsDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Transaction> findByRule(String customQuery) {
		Criteria criteria = new Criteria() {
			@Override
			public Document getCriteriaObject() {
				DBObject obj = new BasicDBObject();
				obj.put("$where", customQuery);
				return new Document(obj.toMap());
			}
		};

		Query query = Query.query(criteria);

		List<Transaction> transactions = null;

		try {
			transactions = mongoTemplate.find(query, Transaction.class);
		} catch (UncategorizedMongoDbException e) {
			System.out.println(e.getCause());
		}
		System.out.println("Custom -> " + transactions);
		return transactions;
	}

	@Override
	public Set<String> getColumns() {

		Query query = new Query();
		Transaction transaction = this.mongoTemplate.findOne(query, Transaction.class);

		return transaction.getData().keySet();
	}

	@Override
	public List<Transaction> findAll() {
		return this.mongoTemplate.findAll(Transaction.class);
	}
}

/*
 * ((this.data.amount > 20000) && (this.data.fee > 0.11)) || (this.data.accepted
 * = true)
 */