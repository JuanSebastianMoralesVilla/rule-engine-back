package com.perficient.challenge.rulesengine.unit_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.perficient.challenge.rulesengine.dao.implementation.RulesDaoImp;
import com.perficient.challenge.rulesengine.model.Rule;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class RuleDaoTest {

	@Mock
	private MongoTemplate mongoTemplate;
	
	@InjectMocks
	private RulesDaoImp rud;

	@Test
	public void findAllTest() {
		List<Rule> rules = Mockito.mock(List.class);
		Mockito.when(mongoTemplate.findAll(Rule.class)).thenReturn(rules);
		assertEquals(rules , rud.findAll());
	}

	@Test
	public void findAllTestNull() {
		Mockito.when(mongoTemplate.findAll(Rule.class)).thenReturn(null);
		assertNull(rud.findAll());
	}
	
	@Test
	public void findAllEmptyList() {
		List<Rule> rules = Mockito.mock(List.class);
		Mockito.when(mongoTemplate.findAll(Rule.class)).thenReturn(rules);
		assertEquals(0, rules.size());
	}
	
	@Test
	public void findAllWithSize() {
		List<Rule> rules = Mockito.mock(List.class);
		Mockito.when(rules.size()).thenReturn(2);
		Mockito.when(mongoTemplate.findAll(Rule.class)).thenReturn(rules);
		assertEquals(2, rud.findAll().size());
	}
	
	@Test
	public void findPinnedNull() {
		Rule rule = mock(Rule.class);
		Mockito.when(rud.findPinned()).thenReturn(null);

		assertNull(rud.findPinned());
	}
	
	@Test
	public void findPinned() {
		var rule = new Rule("asd1231","((first_name=='Nev')AND(amount>=100))OR(accepted==true))",1220227200L,false);

		System.out.println(rud.findAll().toString() + "asda");
		System.out.println(mongoTemplate.insert(rule));
		Mockito.when(mongoTemplate.save(rule)).thenReturn(rule);
		rud.save(rule);
		System.out.println(rud.findAll());
	}
	
}
