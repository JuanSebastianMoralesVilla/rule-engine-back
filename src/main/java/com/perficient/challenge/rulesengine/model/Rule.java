package com.perficient.challenge.rulesengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Rules")
public class Rule{
	 
	@Id
	private String ID;
	
	private int column1;
	
	private String column2;
	
	private String column3;
	
	private boolean column4;
	
	public Rule() {             
		      
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getColumn1() {
		return column1;
	}

	public void setColumn1(int column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public boolean isColumn4() {
		return column4;
	}

	public void setColumn4(boolean column4) {
		this.column4 = column4;
	}
	
	
}
