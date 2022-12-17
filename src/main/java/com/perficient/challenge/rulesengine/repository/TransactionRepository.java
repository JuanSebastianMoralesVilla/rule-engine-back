package com.perficient.challenge.rulesengine.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.perficient.challenge.rulesengine.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{
	
	@Query("{'data.?0': ?1}")
    List<Transaction> getEqualsStringColumn(String column, String value);
	
	
	@Query("{'data.?0': {$gte: ?1}}")
    List<Transaction> getGreaterNumericColumn(String column, Integer value);
	
	@Query("{'data.?0': {$lte: ?1}}")
    List<Transaction> getLowerNumericColumn(String column, Integer value);
	
	@Query("{'data.?0': {$gte: 'data.?1'}}")
    List<Transaction> getGreaterNumericColumn(String column1, String column2);
	
	@Query("{'this.data.?0': {$lte: 'this.data.?1'}}")
    List<Transaction> getLowerNumericColumn(String column, String columnValue);
}
