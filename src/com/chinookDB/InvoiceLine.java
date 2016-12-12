package com.chinookDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceLine {
	public InvoiceLine(int id){
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the invoiceId
	 */
	public int getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the trackId
	 */
	public int getTrackId() {
		return trackId;
	}

	/**
	 * @param trackId the trackId to set
	 */
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public static InvoiceLine fromResultSet(ResultSet rs) throws SQLException{
		InvoiceLine line = new InvoiceLine(rs.getInt("InvoiceLineId"));
		line.setInvoiceId(rs.getInt("InvoiceId"));
		line.setTrackId(rs.getInt("TrackId"));
		line.setUnitPrice(rs.getDouble("UnitPrice"));
		line.setQuantity(rs.getInt("Quantity"));
		
		return line;
	}

	private int id;
	private int invoiceId;
	private int trackId;
	
	private double unitPrice;
	
	private int quantity;
}
