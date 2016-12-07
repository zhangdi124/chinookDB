package com.chinookDB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ApplicationDAO {
	private static ApplicationDAO instance;
	
	private ApplicationDAO(){}
	
	public static ApplicationDAO getInstance(){
		if(instance == null)
			instance = new ApplicationDAO();
		
		return instance;
	}
	public Connection getConnection(){
		String connectionUrl = "jdbc:mysql://localhost:3307/chinook";
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl,"root",null);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return connection;
		
	}
	
	public void closeConnection(Connection connection){
		if(connection == null)
			return;
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
