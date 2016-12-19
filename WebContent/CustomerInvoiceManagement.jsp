<%@page import="com.chinookDB.services.InvoiceService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String command = request.getParameter("command");

	if(command != null){
		InvoiceService service = InvoiceService.getInstance();
		int trackId = Integer.parseInt(request.getParameter("track"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int customerId = (Integer)session.getAttribute("customerId");
		if(command.equals("add")){
			service.add(customerId, trackId, quantity);
			session.setAttribute("message", String.format("Added Track ID %d x %d to Invoice for customer ID %d", trackId, quantity, customerId));
		}else if(command.equals("remove")){
			service.remove(customerId, trackId, quantity);
			session.setAttribute("message", String.format("Removed Track ID %d x %d to Invoice for customer ID %d", trackId, quantity, customerId));
		}
	}

	if(session.getAttribute("login").equals("employee"))
		response.sendRedirect("/chinookDB/shopping-cart.jsp");
	else
		response.sendRedirect("/chinookDB/product-search.jsp");
%>