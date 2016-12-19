<%@page import="com.chinookDB.services.InvoiceService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if(session.getAttribute("customerId") == null)
		return;

	int customerId = (Integer)session.getAttribute("customerId");
	
	InvoiceService service = InvoiceService.getInstance();
	service.submitInvoice(customerId);
	
	response.sendRedirect("/chinookDB/order-complete.jsp");
%>

<h1>Please provide a customerid</h1>