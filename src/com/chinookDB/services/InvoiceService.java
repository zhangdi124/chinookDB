package com.chinookDB.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinookDB.ApplicationDAO;
import com.chinookDB.Invoice;
import com.chinookDB.InvoiceLine;

public class InvoiceService {

	private static InvoiceService instance;
	private InvoiceService(){}
	
	public static InvoiceService getInstance(){
		if(instance == null)
			instance = new InvoiceService();
		
		return instance;
	}
	
	public void remove(int customerId, int trackId, int quantity){
		String sql = "UPDATE invoiceline SET Quantity = GREATEST(0, Quantity - ?) " +
					"where trackId = ? and " + 
					"InvoiceId = (SELECT InvoiceId FROM `invoice` where customerId = ? "+
					"order by InvoiceDate desc LIMIT 1);";
		
		updateInvoice(sql, customerId, trackId, quantity);
	}
	public void add(int customerId, int trackId, int quantity){
		if(invoiceLineExists(customerId, trackId)){
			String sql = "UPDATE invoiceline SET Quantity = Quantity + ? " +
					"where trackId = ? and " + 
					"InvoiceId = (SELECT InvoiceId FROM `invoice` where customerId = ? "+
					"order by InvoiceDate desc LIMIT 1);";
			
			updateInvoice(sql, customerId, trackId, quantity);
			return;
		}
	
		insertInvoiceLine(customerId, trackId, quantity);
	}
	
	private boolean invoiceLineExists(int customerId, int trackId){
		String sql = "SELECT count(*) from invoiceline where TrackId = ? and invoiceId = (select invoiceId from invoice where customerId = ? ORDER BY invoicedate desc LIMIT 1) and QUANTITY > 0;";
		
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;

		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, trackId);
			statement.setInt(2, customerId);
			
			rs = statement.executeQuery();
			
			if(rs.next())
				return rs.getInt(1) > 0;
			
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
		
		return false;
	}
	
	private void insertInvoiceLine(int customerId, int trackId, int quantity){
		String sql = "INSERT INTO invoiceline (InvoiceLineId, InvoiceId, TrackId, UnitPrice, Quantity)" +
				"VALUES (" +
					"?, " + 
					"(SELECT InvoiceId FROM `invoice` where customerId = ? order by InvoiceDate desc LIMIT 1), " + 
					"?, " +
					"(select UnitPrice from track where trackId = ? LIMIT 1), " +
					"?" +
				")";

		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;

		try{
			connection = dao.getConnection();
			
			//Get ID first
			PreparedStatement statement = connection.prepareStatement("(SELECT MAX(InvoiceLineId) from invoiceline)");
			rs = statement.executeQuery();
			
			int id = 0;
			if(rs.next()){
				id = rs.getInt(1) + 1;
			}
			
			statement = connection.prepareStatement(sql);

			statement.setInt(1, id);
			statement.setInt(2, customerId);
			statement.setInt(3, trackId);
			statement.setInt(4, trackId);
			statement.setInt(5, quantity);
			
			statement.execute();
			
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
	}
	private void updateInvoice(String sql, int customerId, int trackId, int quantity){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;

		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, quantity);
			statement.setInt(2, trackId);
			statement.setInt(3, customerId);
			
			statement.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dao.closeConnection(connection);
		}				
	}
	
	//TODO - Some kind of eCommerce logic...
	//for this POC just start a new blank invoice
	public void submitInvoice(int customerId){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
		try{
			connection = dao.getConnection();
			String sql = "SELECT InvoiceId FROM invoice ORDER BY InvoiceId DESC LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			rs = statement.executeQuery();
			int invoiceId = 0;
			if(rs.next())
				invoiceId = rs.getInt(1) + 1;
			
			sql = "INSERT into invoice (InvoiceID, customerId, InvoiceDate, Total) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, invoiceId);
			statement.setInt(2, customerId);
			statement.setDate(3, new Date(new java.util.Date().getTime()));
			statement.setDouble(4, 0D);
			
			statement.execute();
			
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
	}
	
	public Invoice getInvoice(int customerId){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;

		try{
			connection = dao.getConnection();
			String sql = String.format("SELECT * FROM `invoiceline` WHERE invoiceid = (select invoiceid from invoice where customerid = %d order by invoicedate desc limit 1) AND quantity > 0;", customerId);
			PreparedStatement statement = connection.prepareStatement(sql);

			rs = statement.executeQuery();
			
			List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
			while(rs.next()){
				invoiceLines.add(InvoiceLine.fromResultSet(rs));
			}
			
			rs.close();
			
			sql = String.format("SELECT * FROM `invoice` WHERE CustomerId = %d order by InvoiceDate desc", customerId);
			statement = connection.prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			Invoice invoice = null;
			
			if(rs.next()){
				invoice = Invoice.fromResultSet(rs);
				invoice.extractInvoiceLines(invoiceLines);
			}
			
			rs.close();
			
			return invoice;
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
		
		return null;
	}
}
