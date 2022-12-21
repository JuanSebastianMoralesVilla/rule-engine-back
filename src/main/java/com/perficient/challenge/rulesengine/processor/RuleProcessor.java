package com.perficient.challenge.rulesengine.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perficient.challenge.rulesengine.dao.implementation.TransactionsDaoImp;

@Component
public class RuleProcessor {

	@Autowired
	TransactionsDaoImp transactionDao;
	
	private Map<String,String> logicMap;
	private Map<String, String> columnTypes;
	private Map<String, Set<String>> operators;
		   
	public RuleProcessor(TransactionsDaoImp transactionDao, Map<String, String> columnTypes) {
		
		this.transactionDao = transactionDao;
		this.columnTypes = this.transactionDao.getColumnsWithType();
		this.symbolsInit();
	}
	
	public String processRule(String rawRule) {
		
		System.out.println(rawRule);
		
		if(!this.verifyRule(rawRule))
			return "-";
		
		for(String i: logicMap.keySet()) {
			rawRule = rawRule.replaceAll(i, logicMap.get(i));
		}
		
		for(String i: columnTypes.keySet()) {
			
			rawRule = rawRule.replaceAll(i, "this.data."+i);
		}
		
		return rawRule;
	}
	
	//Código vomitivo. Quiero dormir.
	public boolean verifyRule(String rawRule) {
		
		if(rawRule.isBlank())
			return true;
		
		Matcher m = Pattern.compile("\\((.*?)\\)").matcher(rawRule);
		List<String> parts = new ArrayList<String>();
		
		while (m.find()) {
		    
		    String part = m.group(1).replace("(", "").replace(")", "");
		    System.out.println(part);
		    parts.add(part);
		}
		
		Set<String> operators = Set.of("==", ">=", "<=", "!=");
		Set<String> simpleOperators = Set.of("<",">");

		if(parts.isEmpty()) 
			return false;
		
		for(String part: parts) {

			String[] splitted = null;
			String splitOp = null;
			
			for(String op: operators) {
				splitted = part.split(op);
				splitOp = op;
				if(splitted.length == 2) break;
			}
			
			if (splitted == null || splitted.length < 2) {
				for(String ops: simpleOperators) {
					splitted = part.split(ops);
					splitOp = ops;
					if(splitted.length == 2) break;
				}
			}

			Set<String> booleans = Set.of("true", "false");
			
			String typeColumn1 = this.columnTypes.get(splitted[0]);
			String typeColumn2 = this.columnTypes.get(splitted[1]);
			
			if((typeColumn1 != null) && (typeColumn2 != null)) {
				if(!typeColumn1.equalsIgnoreCase(typeColumn2)) 
					return false;
				
				if(!this.operators.get(typeColumn1).contains(splitOp)) 
					return false;
				
			}else if((typeColumn1 != null) || (typeColumn2 != null)){
				
				int index = 0;
				String type = "";
				
				if(typeColumn1 != null) {
					index = 1;
					type = typeColumn1;
				}
				else {
					index = 0;
					type = typeColumn2;
				}
				
				if(!this.operators.get(type).contains(splitOp)) {
					return false;
				}
				
				if(type.equals(String.class.getSimpleName())) {
					if(!splitted[index].contains("\"") && !splitted[index].contains("'")) {
						return false;
					}
				}
				else if(type.equals(Integer.class.getSimpleName()) || type.equals(Double.class.getSimpleName())) {

					try {
						Double.parseDouble(splitted[index]);
					}catch(NumberFormatException e) {
						return false;
					}
				}else {
					if(!booleans.contains(splitted[index]))
						return false;
				}
			}
		}
		return true;
	}
	
	private void symbolsInit() {
		
		this.logicMap = new HashMap<String,String>() {{
		    put("AND", "&&");
		    put("OR", "||");
		}};
		
		Set<String> stringBooleanOperators = Set.of("==", "!=");
		Set<String> numericOperators = Set.of("==", "!=", "<", ">", "<=", ">=");
		
		this.operators = new HashMap<String,Set<String>>() {{
		    put(String.class.getSimpleName(), stringBooleanOperators);
		    put(Double.class.getSimpleName(), numericOperators);
		    put(Integer.class.getSimpleName(), numericOperators);
		    put(Boolean.class.getSimpleName(), stringBooleanOperators);
		}};
		
	}
	
}
