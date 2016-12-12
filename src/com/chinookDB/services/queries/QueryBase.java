package com.chinookDB.services.queries;

public class QueryBase {
	protected String convertToParameter(String s){
		if(s == null || s.trim().isEmpty())
			return null;
		
		return "%" + s.toLowerCase() + "%";
	}
}
