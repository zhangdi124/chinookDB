package com.chinookDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinookDB.beans.Track;

public class Invoice {
	public Invoice(int id){
		this.id = id;
		this.invoiceLines = new ArrayList<InvoiceLine>();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}
	/**
	 * @return the billingCity
	 */
	public String getBillingCity() {
		return billingCity;
	}
	/**
	 * @return the billingState
	 */
	public String getBillingState() {
		return billingState;
	}
	/**
	 * @return the billingCountry
	 */
	public String getBillingCountry() {
		return billingCountry;
	}
	/**
	 * @return the billingPostalCode
	 */
	public String getBillingPostalCode() {
		return billingPostalCode;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	public void setTotal(double total){
		this.total = total;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	/**
	 * @param billingCity the billingCity to set
	 */
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}
	/**
	 * @param billingState the billingState to set
	 */
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}
	/**
	 * @param billingCountry the billingCountry to set
	 */
	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}
	/**
	 * @param billingPostalCode the billingPostalCode to set
	 */
	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}
	
	public List<InvoiceLine> getInvoiceLines(){
		return invoiceLines;
	}
	
	public void extractInvoiceLines(List<InvoiceLine> invoiceLines){
		if(invoiceLines == null)
			return;
		
		this.invoiceLines.addAll(invoiceLines);
	}
	
	public int getQuantity(Track track){
		int trackId = track.getId();
		int q = 0;
		
		for(InvoiceLine line : invoiceLines){
			if(line.getTrack().getId() == trackId)
				q += line.getQuantity();
		}
		
		return q;
	}
	public static Invoice fromResultSet(ResultSet rs) throws SQLException{
		Invoice invoice = new Invoice(rs.getInt("InvoiceId"));
		invoice.setCustomerId(rs.getInt("customerId"));
		invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
		invoice.setBillingAddress(rs.getString("BillingAddress"));
		invoice.setBillingCity(rs.getString("BillingCity"));
		invoice.setBillingState(rs.getString("BillingState"));
		invoice.setBillingCountry(rs.getString("BillingCountry"));
		invoice.setBillingPostalCode(rs.getString("BillingPostalCode"));
		invoice.setTotal(rs.getDouble("Total"));
		
		return invoice;
	}
	
	public double getTotalPrice(){
		double sum = 0D;
		for(InvoiceLine line : invoiceLines){
			sum += line.getUnitPrice() * line.getQuantity();
		}
		return sum;
	}
	
	private int id;
	private int customerId;
	private Date invoiceDate;
	private String billingAddress;
	private String billingCity;
	private String billingState;
	private String billingCountry;
	private String billingPostalCode;
	private double total;
	
	private List<InvoiceLine> invoiceLines;
}
