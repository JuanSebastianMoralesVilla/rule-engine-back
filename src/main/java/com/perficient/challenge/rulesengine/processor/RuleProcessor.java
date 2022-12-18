package com.perficient.challenge.rulesengine.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perficient.challenge.rulesengine.dao.implementation.TransactionsDaoImp;

@Component
public class RuleProcessor {

	@Autowired
	TransactionsDaoImp transactionDao;
	
	private Map<String,String> logicMap;
	
	private Map<String, String> columnMap ;
		   
	public RuleProcessor(TransactionsDaoImp transactionDao, Map<String, String> columnMap) {
		
		this.transactionDao = transactionDao;
		this.logicMap = new HashMap<String,String>() {{
		    put("AND", "&&");
		    put("OR", "||");
		}};
		this.columnMap =  transactionDao.getColumns().stream().collect(Collectors.toMap(
				column -> column, formattedColumn -> "this.data."+formattedColumn));
	}
	
	public String processRule(String rawRule) {
	

		for(String i: logicMap.keySet()) {
			rawRule = rawRule.replaceAll(i, logicMap.get(i));
		}
		
		for(String i: columnMap.keySet()) {
			rawRule = rawRule.replaceAll(i, columnMap.get(i));
		}
		
		System.out.println(rawRule);
		return rawRule;
	}
	
	public boolean verifyRule(String rawRule) {
		
		Pattern pattern = Pattern.compile("\\\\(([^)]+)\\\\)");
	    Matcher matcher = pattern.matcher(rawRule);
	    if (matcher.find()) {
	      System.out.println(matcher.group(1));
	    }
		
		return true;
	}
	
}
