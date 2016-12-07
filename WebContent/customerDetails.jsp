<%@page import="com.chinookDB.CustomerFactory"%>
<%@page import="com.chinookDB.beans.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	CustomerFactory cf = CustomerFactory.getInstance();
	String customerId = request.getParameter("customerId");
	
	Customer customer = null;
	
	if(customerId != null){
		customer = cf.findCustomerById(Integer.parseInt(customerId));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><%=customer.getFirstName() %></h1>
</body>
</html>