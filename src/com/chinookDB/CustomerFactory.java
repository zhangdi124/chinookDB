package com.chinookDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinookDB.beans.Customer;

public class CustomerFactory {
	private static CustomerFactory instance;
	
	private CustomerFactory(){}
	
	public static CustomerFactory getInstance(){
		if(instance == null)
			instance = new CustomerFactory();
		
		return instance;
	}
	
	public List<Customer> getAllCustomers(){
		String sql = "SELECT * FROM `customer` ORDER BY LastName, FirstName";
		return Get(sql);
	}
	
	public List<Customer> getCustomersInCity(String city){
		city = city.toLowerCase() + '%';
		String sql = "SELECT * FROM `customer` where LOWER(city) like '" + city + "' ORDER BY LastName, FirstName";
		return Get(sql);
	}
	
	public Customer findCustomerById(int id){
		String sql = "SELECT * FROM `customer` where CustomerId = " + id + " ORDER BY LastName, FirstName";
		
		List<Customer> result = Get(sql);
		if(result.size() == 0)
			return null;
		
		return result.get(0);
	}
	private List<Customer> Get(String sql){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
		List<Customer> customers = new ArrayList<Customer>();
		
		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			rs = statement.executeQuery();
			
			while(rs.next()){
				Customer customer = parseRowAsCustomer(rs);
				customers.add(customer);
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
		
		return customers;		
	}
	
	private Customer parseRowAsCustomer(ResultSet rs){
		try{
			int id = rs.getInt(1);
			
			Customer customer = new Customer(id);
			customer.setFirstName(rs.getString(2));
			customer.setLastName(rs.getString(3));
			customer.setCompany(rs.getString(4));
			customer.setAddress(rs.getString(5));
			customer.setCity(rs.getString(6));
			customer.setState(rs.getString(7));
			customer.setCountry(rs.getString(8));
			customer.setPostalCode(rs.getString(9));
			customer.setPhone(rs.getString(10));
			customer.setFax(rs.getString(11));
			customer.setEmail(rs.getString(12));
			customer.setSupportRepId(rs.getInt(13));
			
			return customer;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
