package com.chinookDB.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chinookDB.ApplicationDAO;

public class InfoLookupService {
	private static InfoLookupService instance;
	
	private InfoLookupService(){
		lookupCache = new HashMap<String, Object>();
	}
	
	private HashMap<String, Object> lookupCache;
	
	public static InfoLookupService getInstance(){
		if(instance == null)
			instance = new InfoLookupService();
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> lookupGenres(){
		if(!lookupCache.containsKey("GENRES")){
			lookupCache.put("GENRES", fetchGenres());
		}
		
		return (List<String>)lookupCache.get("GENRES");
	}
	
	private List<String> fetchGenres(){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
		List<String> genres = new ArrayList<String>();
		
		String sql = "SELECT distinct name from GENRE";
		
		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			rs = statement.executeQuery();
			
			while(rs.next()){
				genres.add(rs.getString(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			dao.closeConnection(connection);
		}
		
		return genres;
	}
}
