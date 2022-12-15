package com.perficient.challenge.rulesengine.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.perficient.challenge.rulesengine.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{
	
}
