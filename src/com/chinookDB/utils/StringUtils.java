package com.chinookDB.utils;

public final class StringUtils {
	public static String lowerCaseOrNull(String s){
		if(s == null || s.trim().isEmpty())
			return null;
		
		return s.toLowerCase();
	}
}
