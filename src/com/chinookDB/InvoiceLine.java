package com.chinookDB;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinookDB.beans.Track;
import com.chinookDB.services.TrackService;

public class InvoiceLine {
	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}
	/**
	 * @param track the track to set
	 */
	public void setTrack(Track track) {
		this.track = track;
	}
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
		TrackService service = TrackService.getInstance();
		
		InvoiceLine line = new InvoiceLine(rs.getInt("InvoiceLineId"));
		line.setInvoiceId(rs.getInt("InvoiceId"));
		line.setUnitPrice(rs.getDouble("UnitPrice"));
		line.setQuantity(rs.getInt("Quantity"));
		
		Track track = service.findTrackById(rs.getInt("TrackId"));
		line.setTrack(track);
		
		return line;
	}

	private int id;
	private int invoiceId;
	private Track track;
	
	private double unitPrice;
	
	private int quantity;
}
