package com.chinookDB.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinookDB.ApplicationDAO;
import com.chinookDB.beans.Customer;

public class CustomerService {
	private static CustomerService instance;
	private CustomerService(){}
	
	public static CustomerService getInstance(){
		if(instance == null)
			instance = new CustomerService();
		
		return instance;
	}
	
	public List<Customer> getAllCustomers(){
		String sql = "SELECT * FROM `customer` ORDER BY LOWER(LastName), LOWER(FirstName)";
		return get(sql);
	}
	
	public Customer findCustomerById(int id){
		String sql = "SELECT * FROM `customer` where CustomerId=" + id + " ORDER BY LOWER(LastName), LOWER(FirstName)";
		
		List<Customer> matches = get(sql);
		
		if(matches.size() > 0)
			return matches.get(0);
		
		return null;
	}
	private List<Customer> get(String sql){
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
	
	private Customer parseRowAsCustomer(ResultSet rs) throws SQLException{
		int id = rs.getInt("CustomerId");
		String firstName = rs.getString("FirstName");
		String lastName = rs.getString("LastName");
		String company = rs.getString("Company");
		String address = rs.getString("Address");
		String city = rs.getString("City");
		String state = rs.getString("State");
		String postalCode = rs.getString("PostalCode");
		String phone = rs.getString("Phone");
		String fax = rs.getString("Fax");
		String email = rs.getString("Email");
		int supportRepId = rs.getInt("SupportRepId");
		
		Customer customer = new Customer(id);
		customer.setFirstName(firstName);
		customer.setFax(fax);
		customer.setLastName(lastName);
		customer.setCompany(company);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setPostalCode(postalCode);
		customer.setPhone(phone);
		customer.setEmail(email);
		customer.setSupportRepId(supportRepId);
		
		return customer;
	}
}
